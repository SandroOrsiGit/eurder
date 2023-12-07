package com.switchfully.eurder.repository;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.Customer;
import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.domain.Order;
import com.switchfully.eurder.domain.dto.ItemDto;
import com.switchfully.eurder.exceptions.IdNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.jupiter.api.Assertions.*;

class OrderRepositoryTest {

	OrderRepository orderRepository;
	Customer customer;
	Order order;
	ItemDto itemDto;

	@BeforeEach
	void init(){
		orderRepository = new OrderRepository();
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
		itemDto = new ItemDto(UUID.randomUUID(), "lamp", "led", 20, 5);
	}

	@Test
	void whenCreateOrder_thenPopulateRepository(){
		orderRepository.createOrder(order);

		assertThat(orderRepository.getOrders()).contains(entry(order.getOrderId(), order));
	}

	@Test
	void whenGetOrderById_thenReturnOrder(){
		orderRepository.createOrder(order);

		Order actual = orderRepository.getOrderById(order.getOrderId());

		assertThat(actual).isEqualTo(order);
	}

	@Test
	void givenInvalidId_whenGetOrderById_thenThrowIdNotFoundException(){
		assertThatThrownBy(() -> orderRepository.getOrderById(UUID.randomUUID()))
				.isInstanceOf(IdNotFoundException.class)
				.hasMessageContaining("The specified order ID could not be found");
	}

	@Test
	void whenAddItemToOrder_thenUpdateOrder(){
		orderRepository.createOrder(order);

		orderRepository.addItemToOrder(order.getOrderId(), itemDto, 2, true);

		assertThat(orderRepository.getOrderById(order.getOrderId()).getItemGroups()).isNotEmpty();
	}
}