package com.switchfully.eurder.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "items")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private Long itemId;
	private String name;
	private String description;
	private double price;
	private int amountInStock;

	protected Item() {
	}

	public Item(String name, String description, double price, int amountInStock) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.amountInStock = amountInStock;
	}

	public Long getItemId() {
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

	public void reduceAmountInStock(int amount) {
		this.amountInStock -= amount;
	}
}
