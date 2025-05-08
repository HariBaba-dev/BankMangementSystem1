package com.example.crud.operation.Repository;

import java.util.List;
import com.example.crud.operation.entity.Item;

public interface ItemRepository {

	Item save(Item item);
	List<Item> findAll();
}
