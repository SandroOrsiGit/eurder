package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.domain.ItemGroup;
import com.switchfully.eurder.domain.Order;
import com.switchfully.eurder.domain.dto.OrderDto;
import com.switchfully.eurder.exceptions.IdNotFoundException;
import com.switchfully.eurder.mapper.ItemMapper;
import com.switchfully.eurder.mapper.OrderMapper;
import com.switchfully.eurder.repository.ItemGroupRepository;
import com.switchfully.eurder.repository.ItemRepository;
import com.switchfully.eurder.repository.OrderRepository;
import com.switchfully.eurder.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
	OrderRepository orderRepository;
	OrderMapper orderMapper;
	ItemRepository itemRepository;
	ItemMapper itemMapper;
	UserRepository userRepository;
	ItemGroupRepository itemGroupRepository;

	public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, ItemRepository itemRepository, ItemMapper itemMapper, UserRepository userRepository, ItemGroupRepository itemGroupRepository) {
		this.orderRepository = orderRepository;
		this.orderMapper = orderMapper;
		this.itemRepository = itemRepository;
		this.itemMapper = itemMapper;
		this.userRepository = userRepository;
		this.itemGroupRepository = itemGroupRepository;
	}

	public OrderDto createOrder(Long userId){
		Order order = new Order(userRepository.findUserByUserId(userId)
				.orElseThrow(() -> new IdNotFoundException("User with id " + userId + " not found")));
		orderRepository.save(order);

		return orderMapper.mapOrderToOrderDto(order);
	}

	public OrderDto addItemToOrder(Long orderId, Long itemId, int amount) {
		Item item = itemRepository.findItemByItemId(itemId)
				.orElseThrow(() -> new IdNotFoundException("Item with id " + itemId + " could not be found"));
		item.reduceAmountInStock(amount);

		Order order = orderRepository.findOrderByOrderId(orderId)
				.orElseThrow(() -> new IdNotFoundException("Item with id " + itemId + " could not be found"));

		order.increaseTotalPrice(item.getPrice() * amount);

		itemGroupRepository.save(new ItemGroup(amount, item.getAmountInStock() > 0, item, order));

		return orderMapper.mapOrderToOrderDto(order);
	}

	public List<OrderDto> getOrders() {
		return orderRepository.findAll().stream()
				.map(order -> orderMapper.mapOrderToOrderDto(order))
				.collect(Collectors.toList());
	}
}
