package com.switchfully.eurder.repository;

import com.switchfully.eurder.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class ItemRepository {
	private final Map<UUID, Item> items;

	public ItemRepository() {
		this.items = new HashMap<>();
	}

	public List<Item> getItems() {
		return items.values().stream().toList();
	}

	public void createItem(Item item) {
		items.put(item.getItemId(), item);
	}
}
