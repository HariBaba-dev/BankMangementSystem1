package com.example.crud.operation.controller;

import com.example.crud.operation.entity.Item;
import com.example.crud.operation.service.ItemService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemControllerImpl implements ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        Item savedItem = itemService.addItem(item);
        return ResponseEntity.ok(savedItem); 
    }

    @GetMapping
	public List<Item> getAllItems() {
		// TODO Auto-generated method stub
		return itemService.getAllItems();
	}
}



