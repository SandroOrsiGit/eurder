package com.switchfully.eurder.repository;

import com.switchfully.eurder.domain.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
	ItemRepository itemRepository;
	Item item;

	@BeforeEach
	void init(){
		itemRepository = new ItemRepository();
		item = new Item("Sofa", "A comfy sofa", 100, 6);
	}

	@Test
	void whenCreateItem_thenPopulateRepository(){
		itemRepository.createItem(item);

		assertThat(itemRepository.getItems()).contains(item);
	}

}