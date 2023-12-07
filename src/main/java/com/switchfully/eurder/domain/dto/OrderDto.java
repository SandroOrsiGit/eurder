package com.switchfully.eurder.domain.dto;

import com.switchfully.eurder.domain.Customer;
import com.switchfully.eurder.domain.ItemGroup;
import com.switchfully.eurder.domain.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class OrderDto {
	private final UUID orderId;
	Customer customer;
	private final List<ItemGroup> itemGroups;

	public OrderDto(UUID orderId, Customer customer, List<ItemGroup> itemGroups) {
		this.orderId = orderId;
		this.customer = customer;
		this.itemGroups = itemGroups;
	}

	public UUID getOrderId() {
		return orderId;
	}

	public List<ItemGroup> getItemGroups() {
		return itemGroups;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OrderDto orderDto = (OrderDto) o;
		return Objects.equals(orderId, orderDto.orderId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId);
	}
}
