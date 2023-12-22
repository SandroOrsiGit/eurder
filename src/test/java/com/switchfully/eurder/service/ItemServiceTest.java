package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.domain.dto.CreateItemDto;
import com.switchfully.eurder.mapper.ItemMapper;
import com.switchfully.eurder.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ItemServiceTest {
	ItemMapper itemMapper;
	ItemRepository itemRepository;
	ItemService itemService;
	Item item;
	CreateItemDto createItemDto;

	@BeforeEach
	void init() {
		itemRepository = mock(ItemRepository.class);
		itemMapper = mock(ItemMapper.class);
		itemService = new ItemService(itemRepository, itemMapper);
		item = new Item("Sofa", "A comfy sofa", 100, 6);
		createItemDto = new CreateItemDto("Bed", "king sized bed", 300, 3);
	}

	@Test
	void whenCreateItem_thenCallRepositoryAndMapper(){
		itemService.createItem(createItemDto);

		verify(itemMapper).mapCreateItemDtoToItem(createItemDto);
		verify(itemRepository).save(itemMapper.mapCreateItemDtoToItem(createItemDto));
	}
}