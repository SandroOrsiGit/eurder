package com.switchfully.eurder.mapper;

import com.switchfully.eurder.domain.Customer;
import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.domain.dto.CreateCustomerDto;
import com.switchfully.eurder.domain.dto.CreateItemDto;
import com.switchfully.eurder.domain.dto.CustomerDto;
import com.switchfully.eurder.domain.dto.ItemDto;
import org.springframework.stereotype.Service;

@Service
public class ItemMapper {

	public Item mapCreateItemDtoToItem(CreateItemDto createItemDto) {
		return new Item(createItemDto.getName(),
				createItemDto.getDescription(),
				createItemDto.getPrice(),
				createItemDto.getAmountInStock());
	}

	public ItemDto mapItemToItemDto(Item item){
		return new ItemDto(item.getItemId(),
				item.getName(),
				item.getDescription(),
				item.getPrice(),
				item.getAmountInStock());
	}
}
