package com.switchfully.eurder.api;

import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.domain.dto.CreateItemDto;
import com.switchfully.eurder.mapper.ItemMapper;
import com.switchfully.eurder.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ItemControllerTest {
	ItemService itemService;
	ItemController itemController;
	CreateItemDto createItemDto;

	@BeforeEach
	void init(){
		itemService = mock(ItemService.class);
		itemController = new ItemController(itemService);
		createItemDto = new CreateItemDto("Bed", "king sized bed", 300, 3);
	}

	@Test
	void whenCreateItem_thenCallService(){
		itemController.createItem(createItemDto);

		verify(itemService).createItem(createItemDto);
	}

}