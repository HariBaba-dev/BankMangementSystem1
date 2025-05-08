package com.example.crud.operation.controller;

import com.example.crud.operation.entity.Item;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ItemController {
    ResponseEntity<Item> addItem(Item item);
    List<Item> getAllItems();
}
