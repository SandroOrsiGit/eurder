package com.switchfully.eurder.domain.dto;

import com.switchfully.eurder.domain.ItemGroup;
import com.switchfully.eurder.domain.User;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class OrderDto {
	private final Long orderId;
	private final double totalPrice;
	private final List<ItemGroup> itemGroups;
	private final User user;

	public OrderDto(Long orderId, double totalPrice, User user, List<ItemGroup> itemGroups) {
		this.orderId = orderId;
		this.totalPrice = totalPrice;
		this.user = user;
		this.itemGroups = itemGroups;
	}

	public Long getOrderId() {
		return orderId;
	}

	public List<ItemGroup> getItemGroups() {
		return itemGroups;
	}

	public User getUser() {
		return user;
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

	public double getTotalPrice() {
		return totalPrice;
	}
}
