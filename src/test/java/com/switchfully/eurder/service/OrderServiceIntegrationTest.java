package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.Customer;
import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.domain.Order;
import com.switchfully.eurder.domain.dto.ItemDto;
import com.switchfully.eurder.domain.dto.OrderDto;
import com.switchfully.eurder.mapper.ItemMapper;
import com.switchfully.eurder.mapper.OrderMapper;
import com.switchfully.eurder.repository.ItemRepository;
import com.switchfully.eurder.repository.OrderRepository;
import com.switchfully.eurder.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
class OrderServiceIntegrationTest {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderMapper orderMapper;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	ItemMapper itemMapper;

	@Autowired
	UserRepository userRepository;

	@Autowired
	OrderService orderService;

	Customer customer;
	Order order;
	Item item;

	@BeforeEach
	void init(){
		customer = new Customer(
				"Karel",
				"Polmark",
				"karel@switchfully.be",
				new Address("Brussel",
						"Keizerslaan",
						"155",
						"1000"),
				"0495123456");
		order = new Order(customer);
		item = new Item("Sofa", "A comfy sofa", 100, 6);
	}

	@Test
	void whenCreateOrder_thenPopulateRepository(){
		userRepository.createCustomer(customer);
		orderService.createOrder(customer.getCustomerId());

		assertThat(orderRepository.getOrders().size()).isNotEqualTo(0);
	}

	@Test
	void whenAddItemToOrder_thenReturnOrderDtoAndCallItemRepositoryOrderRepositoryAndOrderMapper(){
		itemRepository.createItem(item);
		orderRepository.createOrder(order);
		userRepository.createCustomer(customer);
		int amountOrdered = 3;
		int initialStock = item.getAmountInStock();

		OrderDto actual = orderService.addItemToOrder(order.getOrderId(), item.getItemId(), amountOrdered);

		assertThat(item.getAmountInStock()).isEqualTo(initialStock - amountOrdered);
		assertThat(actual.getItemGroups().get(0).getItemDto()).isEqualTo(itemMapper.mapItemToItemDto(item));
		assertThat(actual).isEqualTo(orderMapper.mapOrderToOrderDto(order));
	}
}