package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.Customer;
import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.domain.Order;
import com.switchfully.eurder.domain.dto.ItemDto;
import com.switchfully.eurder.mapper.ItemMapper;
import com.switchfully.eurder.mapper.OrderMapper;
import com.switchfully.eurder.repository.ItemRepository;
import com.switchfully.eurder.repository.OrderRepository;
import com.switchfully.eurder.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderServiceTest {
	OrderRepository orderRepository;
	OrderMapper orderMapper;
	ItemRepository itemRepository;
	ItemMapper itemMapper;
	UserRepository userRepository;
	OrderService orderService;
	Order order;
	Item item;
	Customer customer;

	@BeforeEach
	void init(){
		orderRepository = mock(OrderRepository.class);
		orderMapper = mock(OrderMapper.class);
		itemRepository = mock(ItemRepository.class);
		itemMapper = mock(ItemMapper.class);
		userRepository = mock(UserRepository.class);
		orderService = new OrderService(orderRepository, orderMapper, itemRepository, itemMapper, userRepository);
		customer = new Customer(
				"Karel",
				"Polmark",
				"karel@switchfully.be",
				new Address("Brussel",
						"Keizerslaan",
						"155",
						"1000"),
				"0495123456");
		item = new Item("Sofa", "A comfy sofa", 100, 6);
		order = new Order(customer);
	}

	@Test
	void whenCreateOrder_thenCallRepositoryAndMapper(){
		when(userRepository.getCustomerById(customer.getCustomerId())).thenReturn(customer);
		orderService.createOrder(customer.getCustomerId());

		verify(orderRepository).createOrder(any());
		verify(orderMapper).mapOrderToOrderDto(any());
	}

	@Test
	void whenAddItemToOrder_thenReturnOrderDtoAndCallItemRepositoryOrderRepositoryAndOrderMapper(){
		int amountOrdered = 3;
		orderService.addItemToOrder(order.getOrderId(), item.getItemId(), amountOrdered);

		verify(itemRepository).reduceStockOnItemById(item.getItemId(), amountOrdered);
		verify(orderMapper).mapOrderToOrderDto(any());
	}

}