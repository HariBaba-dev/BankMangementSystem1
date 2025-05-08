package com.example.crud.operation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.crud.operation.Repository.ItemRepository;
import com.example.crud.operation.entity.Item;
import com.example.crud.operation.service.ItemServiceImpl;

@SpringBootTest
public class ItemServiceImplTest {

	@Mock
	private ItemRepository itemRepository; // Mocked interface, not Impl

	private ItemServiceImpl itemService;

	private Item sampleItem;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); // Initialize mocks

		itemService = new ItemServiceImpl(itemRepository);

		sampleItem = new Item();
		sampleItem.setId(1L);
		sampleItem.setItemName("Test Item");
		sampleItem.setPrice(10.0);
		sampleItem.setStock(5);
	}

	@Test
	void testAddItem_ShouldCallSaveAndReturnItem() {
		when(itemRepository.save(any(Item.class))).thenReturn(sampleItem);

		Item newItem = new Item();
		newItem.setItemName("Test Item");
		newItem.setPrice(10.0);
		newItem.setStock(5);

		Item result = itemService.addItem(newItem);

		assertNotNull(result);
		assertEquals("Test Item", result.getItemName());
		assertEquals(10.0, result.getPrice());
		verify(itemRepository, times(1)).save(any(Item.class));
	}

	@Test
	public void testGetAllItems() {
		List<Item> itemList = Arrays.asList(sampleItem);
		when(itemRepository.findAll()).thenReturn(itemList);

		List<Item> items = itemService.getAllItems();

		assertEquals(1, items.size());
		assertEquals("Test Item", items.get(0).getItemName());
		verify(itemRepository, times(1)).findAll();
	}

}
