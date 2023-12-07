package com.switchfully.eurder.repository;

import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.domain.ItemGroup;
import com.switchfully.eurder.domain.Order;
import com.switchfully.eurder.domain.dto.ItemDto;
import com.switchfully.eurder.exceptions.IdNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class OrderRepository {
	Map<UUID, Order> orders;

	public OrderRepository() {
		orders = new HashMap<>();
	}

	public Map<UUID, Order> getOrders() {
		return orders;
	}

	public void createOrder(Order order) {
		orders.put(order.getOrderId(), order);
	}

	public Order getOrderById(UUID orderId) {
		return Optional.ofNullable(orders.get(orderId)).orElseThrow(() -> new IdNotFoundException("The specified order ID could not be found"));
	}

	public void addItemToOrder(UUID orderId, ItemDto itemDto, int amount, boolean isItemInStock) {
		getOrderById(orderId).addItemGroup(new ItemGroup(itemDto.getItemId(), itemDto, amount, isItemInStock));
	}

}
