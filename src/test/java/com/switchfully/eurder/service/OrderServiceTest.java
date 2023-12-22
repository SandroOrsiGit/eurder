package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.domain.Order;
import com.switchfully.eurder.domain.User;
import com.switchfully.eurder.mapper.ItemMapper;
import com.switchfully.eurder.mapper.OrderMapper;
import com.switchfully.eurder.repository.ItemGroupRepository;
import com.switchfully.eurder.repository.ItemRepository;
import com.switchfully.eurder.repository.OrderRepository;
import com.switchfully.eurder.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

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
	ItemGroupRepository itemGroupRepository;
	OrderService orderService;
	Order order;
	Item item;
	User user;

	@BeforeEach
	void init(){
		orderRepository = mock(OrderRepository.class);
		orderMapper = mock(OrderMapper.class);
		itemRepository = mock(ItemRepository.class);
		itemMapper = mock(ItemMapper.class);
		userRepository = mock(UserRepository.class);
		itemGroupRepository = mock(ItemGroupRepository.class);
		orderService = new OrderService(orderRepository, orderMapper, itemRepository, itemMapper, userRepository, itemGroupRepository);
		user = new User(
				"Karel",
				"Polmark",
				"karel@switchfully.be",
				new Address("Brussel",
						"Keizerslaan",
						"155",
						"1000"),
				"0495123456");
		item = new Item("Sofa", "A comfy sofa", 100, 6);
		order = new Order(user);
	}

	@Test
	void whenCreateOrder_thenCallRepositoryAndMapper(){
		when(userRepository.findUserByUserId(user.getUserId())).thenReturn(Optional.of(user));
		orderService.createOrder(user.getUserId());

		verify(orderRepository).save(any());
		verify(orderMapper).mapOrderToOrderDto(any());
	}

	@Test
	void whenAddItemToOrder_thenReturnOrderDtoAndCallItemRepositoryOrderRepositoryAndOrderMapper(){
		when(itemRepository.findItemByItemId(item.getItemId())).thenReturn(Optional.of(item));
		when(orderRepository.findOrderByOrderId(order.getOrderId())).thenReturn(Optional.of(order));
		int amountOrdered = 3;
		orderService.addItemToOrder(order.getOrderId(), item.getItemId(), amountOrdered);

		verify(orderMapper).mapOrderToOrderDto(any());
		verify(itemGroupRepository).save(any());
	}

}