package com.switchfully.eurder.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
	private final UUID orderId;
	private final Customer customer;
	private final List<ItemGroup> itemGroups;
	private double totalPrice;

	public Order(Customer customer) {
		orderId = UUID.randomUUID();
		this.customer = customer;
		itemGroups = new ArrayList<>();
		this.totalPrice = 0;
	}

	public UUID getOrderId() {
		return orderId;
	}

	public List<ItemGroup> getItemGroups() {
		return itemGroups;
	}

	public Customer getCustomer() {
		return customer;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void addItemGroup(ItemGroup itemGroup){
		itemGroups.add(itemGroup);
		totalPrice += itemGroup.getAmount() * itemGroup.getItemDto().getPrice();
	}
}
