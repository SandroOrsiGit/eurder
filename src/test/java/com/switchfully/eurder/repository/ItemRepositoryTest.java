package com.switchfully.eurder.repository;

import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.exceptions.IdNotFoundException;
import com.switchfully.eurder.exceptions.StockTooLowException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
	ItemRepository itemRepository;
	Item item;

	@BeforeEach
	void init(){
		itemRepository = new ItemRepository();
		item = new Item("Sofa", "A comfy sofa", 100, 2);
	}

	@Test
	void whenCreateItem_thenPopulateRepository(){
		itemRepository.createItem(item);

		assertThat(itemRepository.getItems()).contains(item);
	}

	@Test
	void whenGetItemById_thenReturnItem(){
		itemRepository.createItem(item);

		Item actual = itemRepository.getItemById(item.getItemId());

		assertThat(actual).isEqualTo(item);
	}

	@Test
	void givenInvalidId_whenGetItemById_thenThrowIdNotFoundException(){
		assertThatThrownBy(() -> itemRepository.getItemById(UUID.randomUUID()))
				.isInstanceOf(IdNotFoundException.class)
				.hasMessageContaining("The specified item ID could not be found");
	}

	@Test
	void givenItemInStock_whenIsItemInStock_thenReturnTrue(){
		itemRepository.createItem(item);

		boolean actual = itemRepository.isItemInStock(item.getItemId());

		assertThat(actual).isTrue();
	}

	@Test
	void givenItemNotInStock_whenIsItemInStock_thenReturnFalse(){
		Item itemNotInStock = new Item("table", "big round table", 69, 0);
		itemRepository.createItem(itemNotInStock);

		boolean actual = itemRepository.isItemInStock(itemNotInStock.getItemId());

		assertThat(actual).isFalse();
	}

	@Test
	void whenReduceStockOnItemById_thenReduceStock(){
		itemRepository.createItem(item);
		int amountInStock = item.getAmountInStock();
		int stockReduction = 2;

		itemRepository.reduceStockOnItemById(item.getItemId(), stockReduction);

		assertThat(item.getAmountInStock()).isEqualTo(amountInStock - stockReduction);
	}

	@Test
	void givenLowStock_whenReduceStockOnItemById_thenThrowStockTooLowException(){
		itemRepository.createItem(item);

		assertThatThrownBy(() -> itemRepository.reduceStockOnItemById(item.getItemId(), 3))
				.isInstanceOf(StockTooLowException.class)
				.hasMessageContaining("Stock is too low");
	}

}