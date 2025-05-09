package com.example.crud.operation;

import com.example.crud.operation.controller.ItemControllerImpl;
import com.example.crud.operation.entity.Item;
import com.example.crud.operation.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(ItemControllerImpl.class)
public class ItemControllerImplTest {

	@InjectMocks
	private ItemControllerImpl  itemController;

	@Autowired
	private MockMvc mockMvc;

	private Item sampleItem;

	@MockBean
	private ItemService itemService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testAddItem() throws Exception {
		Item item = new Item();
		item.setId(1L);
		item.setItemName("Test Item");
		item.setPrice(10.0);
		item.setStock(5);

		when(itemService.addItem(any(Item.class))).thenReturn(item);

		mockMvc.perform(
				post("/items").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(item)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.itemName").value("Test Item")).andExpect(jsonPath("$.price").value(10.0))
				.andExpect(jsonPath("$.stock").value(5));
	}

	@Test
	void testGetAllItems() throws Exception {
		Item item = new Item();
		item.setId(1L);
		item.setItemName("Test Item");
		item.setPrice(10.0);
		item.setStock(5);

		List<Item> itemList = Arrays.asList(item);

		when(itemService.getAllItems()).thenReturn(itemList);

		mockMvc.perform(get("/items")).andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].itemName").value("Test Item")).andExpect(jsonPath("$[0].price").value(10.0))
				.andExpect(jsonPath("$[0].stock").value(5));
	}

	@Test
	void testGetItemById() throws Exception {
	    Item item = new Item();
	    item.setId(1L);
	    item.setItemName("Test Item");
	    item.setPrice(10.0);
	    item.setStock(5);

	    when(itemService.getItemById(1L)).thenReturn(item);

	    mockMvc.perform(get("/items/1"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.id").value(1))
	            .andExpect(jsonPath("$.itemName").value("Test Item"))
	            .andExpect(jsonPath("$.price").value(10.0))
	            .andExpect(jsonPath("$.stock").value(5));
	}

}
