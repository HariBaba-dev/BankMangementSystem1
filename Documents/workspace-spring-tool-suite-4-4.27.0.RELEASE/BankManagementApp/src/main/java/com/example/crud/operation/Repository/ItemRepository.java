package com.example.crud.operation.Repository;

import java.util.List;
import java.util.Optional;

import com.example.crud.operation.entity.Item;

public interface ItemRepository {

	Item save(Item item);
	List<Item> findAll();
	Optional<Item> findById(Long id);
}
