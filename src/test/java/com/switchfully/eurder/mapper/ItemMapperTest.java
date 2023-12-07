package com.switchfully.eurder.mapper;

import com.switchfully.eurder.domain.Customer;
import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.domain.dto.CreateItemDto;
import com.switchfully.eurder.domain.dto.CustomerDto;
import com.switchfully.eurder.domain.dto.ItemDto;
import com.switchfully.eurder.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemMapperTest {

	ItemMapper itemMapper;
	Item item;
	CreateItemDto createItemDto;

	@BeforeEach
	void init(){
		itemMapper = new ItemMapper();
		item = new Item("Sofa", "A comfy sofa", 100, 6);
		createItemDto = new CreateItemDto("Bed", "king sized bed", 300, 3);
	}

	@Test
	void whenMapCreateItemDtoToItem_thenReturnItem(){
		Item actual = itemMapper.mapCreateItemDtoToItem(createItemDto);

		assertThat(actual).isNotNull().isInstanceOf(Item.class);
		assertThat(actual.getName()).isEqualTo(createItemDto.getName());
		assertThat(actual.getDescription()).isEqualTo(createItemDto.getDescription());
		assertThat(actual.getPrice()).isEqualTo(createItemDto.getPrice());
		assertThat(actual.getAmountInStock()).isEqualTo(createItemDto.getAmountInStock());
	}

	@Test
	void whenMapItemToItemDto_thenReturnIemDto(){
		ItemDto actual = itemMapper.mapItemToItemDto(item);

		assertThat(actual).isNotNull().isInstanceOf(ItemDto.class);
		assertThat(actual.getItemId()).isEqualTo(item.getItemId());
		assertThat(actual.getName()).isEqualTo(item.getName());
		assertThat(actual.getDescription()).isEqualTo(item.getDescription());
		assertThat(actual.getPrice()).isEqualTo(item.getPrice());
		assertThat(actual.getAmountInStock()).isEqualTo(item.getAmountInStock());
	}


}