package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.dto.CreateItemDto;
import com.switchfully.eurder.domain.dto.ItemDto;
import com.switchfully.eurder.mapper.ItemMapper;
import com.switchfully.eurder.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ItemServiceIntegrationTest {
	@Autowired
	ItemRepository itemRepository;

	@Autowired
	ItemMapper itemMapper;

	@Autowired
	ItemService itemService;

	CreateItemDto createItemDto;

	@BeforeEach
	void init(){
		createItemDto = new CreateItemDto("Bed", "king sized bed", 300, 3);
	}

	@Test
	void whenCreateItem_thenReturnItemDtoAndPopulateRepository(){
		ItemDto actual = itemService.createItem(createItemDto);

		assertThat(itemRepository.getItems().size()).isNotEqualTo(0);

		assertThat(actual.getName()).isEqualTo(createItemDto.getName());
		assertThat(actual.getDescription()).isEqualTo(createItemDto.getDescription());
		assertThat(actual.getPrice()).isEqualTo(createItemDto.getPrice());
		assertThat(actual.getAmountInStock()).isEqualTo(createItemDto.getAmountInStock());
	}
}