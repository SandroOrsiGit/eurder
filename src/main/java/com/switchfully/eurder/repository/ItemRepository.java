package com.switchfully.eurder.repository;

import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.exceptions.IdNotFoundException;
import com.switchfully.eurder.exceptions.StockTooLowException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

	public Item getItemById(UUID itemId){
		var temp = items.get(itemId);
		return Optional.ofNullable(items.get(itemId)).orElseThrow(() -> new IdNotFoundException("The specified item ID could not be found"));
	}

	public boolean isItemInStock(UUID itemId){
		return getItemById(itemId).getAmountInStock() > 0;
	}

	public void reduceStockOnItemById(UUID itemId, int amount){
		if(getItemById(itemId).getAmountInStock() < amount){
			throw new StockTooLowException("Stock is too low");
		}
		getItemById(itemId).reduceAmountInStock(amount);
	}
}
