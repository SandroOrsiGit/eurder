package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.domain.dto.CreateItemDto;
import com.switchfully.eurder.domain.dto.ItemDto;
import com.switchfully.eurder.mapper.ItemMapper;
import com.switchfully.eurder.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemService {
	ItemRepository itemRepository;
	ItemMapper itemMapper;

	public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
		this.itemRepository = itemRepository;
		this.itemMapper = itemMapper;
	}

	public ItemDto createItem(CreateItemDto createItemDto) {
		Item item = itemMapper.mapCreateItemDtoToItem(createItemDto);

		itemRepository.save(item);

		return itemMapper.mapItemToItemDto(item);
	}

	public List<ItemDto> getItems() {
		return itemRepository.findAll()
				.stream()
				.map(item -> itemMapper.mapItemToItemDto(item))
				.collect(Collectors.toList());
	}
}
