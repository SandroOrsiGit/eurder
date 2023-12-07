package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.Order;
import com.switchfully.eurder.domain.dto.OrderDto;
import com.switchfully.eurder.mapper.ItemMapper;
import com.switchfully.eurder.mapper.OrderMapper;
import com.switchfully.eurder.repository.ItemRepository;
import com.switchfully.eurder.repository.OrderRepository;
import com.switchfully.eurder.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {
	OrderRepository orderRepository;
	OrderMapper orderMapper;
	ItemRepository itemRepository;
	ItemMapper itemMapper;
	UserRepository userRepository;

	public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, ItemRepository itemRepository, ItemMapper itemMapper, UserRepository userRepository) {
		this.orderRepository = orderRepository;
		this.orderMapper = orderMapper;
		this.itemRepository = itemRepository;
		this.itemMapper = itemMapper;
		this.userRepository = userRepository;
	}

	public OrderDto createOrder(UUID customerId){
		Order order = new Order(userRepository.getCustomerById(customerId));
		orderRepository.createOrder(order);

		return orderMapper.mapOrderToOrderDto(order);
	}

	public OrderDto addItemToOrder(UUID orderId, UUID itemId, int amount) {
		itemRepository.reduceStockOnItemById(itemId, amount);

		orderRepository.addItemToOrder(orderId,
				itemMapper.mapItemToItemDto(itemRepository.getItemById(itemId)),
				amount,
				itemRepository.isItemInStock(itemId));


		return orderMapper.mapOrderToOrderDto(orderRepository.getOrderById(orderId));
	}
}
