package com.switchfully.eurder.api;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.domain.Order;
import com.switchfully.eurder.domain.User;
import com.switchfully.eurder.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class OrderControllerTest {
	OrderService orderService;
	OrderController orderController;
	User user;
	Order order;
	Item item;

	@BeforeEach
	void init(){
		orderService = mock(OrderService.class);
		orderController = new OrderController(orderService);
		user = new User(
				"Karel",
				"Polmark",
				"karel@switchfully.be",
				new Address("Brussel",
						"Keizerslaan",
						"155",
						"1000"),
				"0495123456");
		order = new Order(user);
		item = new Item("Sofa", "A comfy sofa", 100, 6);
	}

	@Test
	void whenCreateOrder_thenCallService(){
		orderController.createOrder(user.getUserId());

		verify(orderService).createOrder(user.getUserId());
	}

	@Test
	void whenAddItemToOrder_thenCallService(){
		orderController.addItemToOrder(order.getOrderId(), item.getItemId(), 4);

		verify(orderService).addItemToOrder(order.getOrderId(), item.getItemId(), 4);
	}

}