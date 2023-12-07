package com.switchfully.eurder.domain.dto;

import java.util.UUID;

public class ItemDto {
	private final UUID itemId;
	private final String name, description;
	private final double price;
	private final int amountInStock;

	public ItemDto(UUID itemId, String name, String description, double price, int amountInStock) {
		this.itemId = itemId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.amountInStock = amountInStock;
	}

	public UUID getItemId() {
		return itemId;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public int getAmountInStock() {
		return amountInStock;
	}
}
