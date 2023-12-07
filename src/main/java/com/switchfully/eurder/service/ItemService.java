package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.domain.dto.CreateItemDto;
import com.switchfully.eurder.domain.dto.ItemDto;
import com.switchfully.eurder.mapper.ItemMapper;
import com.switchfully.eurder.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
	ItemRepository itemRepository;
	ItemMapper itemMapper;

	public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
		this.itemRepository = itemRepository;
		this.itemMapper = itemMapper;
	}

	public ItemDto createItem(CreateItemDto createItemDto) {
		Item item = itemMapper.mapCreateItemDtoToItem(createItemDto);

		itemRepository.createItem(item);

		return itemMapper.mapItemToItemDto(item);
	}
}
