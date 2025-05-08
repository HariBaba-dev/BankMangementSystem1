package com.example.crud.operation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.crud.operation.Repository.ItemRepository;
import com.example.crud.operation.entity.Item;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    // Constructor injection
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}
