package com.switchfully.eurder.domain;

import com.switchfully.eurder.domain.dto.ItemDto;

import java.time.LocalDate;
import java.util.UUID;

public class ItemGroup {
	private final UUID itemId;
	private final ItemDto itemDto;
	private final int amount;
	private final LocalDate shippingDate;

	public ItemGroup(UUID itemId, ItemDto itemDto, int amount, boolean isInStock) {
		this.itemId = itemId;
		this.itemDto = itemDto;
		this.amount = amount;
		this.shippingDate = LocalDate.now().plusDays(isInStock ? 1 : 7);
	}

	public UUID getItemId() {
		return itemId;
	}

	public ItemDto getItemDto() {
		return itemDto;
	}

	public int getAmount() {
		return amount;
	}

	public LocalDate getShippingDate() {
		return shippingDate;
	}
}
