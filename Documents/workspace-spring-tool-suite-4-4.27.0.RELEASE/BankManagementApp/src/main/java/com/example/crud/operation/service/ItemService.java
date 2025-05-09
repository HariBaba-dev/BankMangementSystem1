package com.example.crud.operation.service;

import java.util.List;

import com.example.crud.operation.entity.Item;

public interface ItemService {
	   Item addItem(Item item);
	    List<Item> getAllItems();
	    Item getItemById(Long id);
}
