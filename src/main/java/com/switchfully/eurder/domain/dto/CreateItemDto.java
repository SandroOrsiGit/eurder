package com.switchfully.eurder.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CreateItemDto {
	@NotNull
	@NotEmpty
	private final String name, description;
	@NotBlank
	private final double price;
	@NotBlank
	private final int amountInStock;

	public CreateItemDto(String name, String description, double price, int amountInStock) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.amountInStock = amountInStock;
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
