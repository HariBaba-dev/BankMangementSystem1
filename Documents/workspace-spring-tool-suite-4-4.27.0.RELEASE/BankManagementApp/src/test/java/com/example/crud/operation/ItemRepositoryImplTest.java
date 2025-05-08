package com.example.crud.operation;

import com.example.crud.operation.Repository.ItemRepositoryImpl;
import com.example.crud.operation.entity.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest

public class ItemRepositoryImplTest {

	private JdbcTemplate jdbcTemplate;
	private ItemRepositoryImpl itemRepository;
	
	  private Item sampleItem;

	@BeforeEach
	void setUp() {
		jdbcTemplate = mock(JdbcTemplate.class);
		itemRepository = new ItemRepositoryImpl(jdbcTemplate); 
	}

	@Test
	void testSaveNewItem_ShouldInsertAndSetId() {
		Item item = new Item();
		item.setItemName("Test Item");
		item.setPrice(10.0);
		item.setStock(5);

		doAnswer(invocation -> {
			KeyHolder keyHolder = invocation.getArgument(1);
			keyHolder.getKeyList().add(Map.of("id", 1L));
			return 1;
		}).when(jdbcTemplate).update(any(), any(KeyHolder.class));

		Item result = itemRepository.save(item);

		assertNotNull(result);
		assertEquals(1L, result.getId());
		assertEquals("Test Item", result.getItemName());
	}

	@Test
	void testSaveExistingItem_ShouldUpdate() {
		Item item = new Item();
		item.setId(100L);
		item.setItemName("Updated Item");
		item.setPrice(20.0);
		item.setStock(10);

		when(jdbcTemplate.update(anyString(), any(), any(), any(), any())).thenReturn(1);

		Item result = itemRepository.save(item);

		assertEquals(100L, result.getId());
		assertEquals("Updated Item", result.getItemName());

		verify(jdbcTemplate, times(1)).update(eq("UPDATE item SET item_name = ?, price = ?, stock = ? WHERE id = ?"),
				eq("Updated Item"), eq(20.0), eq(10), eq(100L));
	}
    @Test
    void testFindAll() {
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class)))
                .thenReturn(Arrays.asList(sampleItem));
        List<Item> items = itemRepository.findAll();
        assertEquals(1, items.size());
    }
}
