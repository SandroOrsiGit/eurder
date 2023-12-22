package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.domain.Order;
import com.switchfully.eurder.domain.User;
import com.switchfully.eurder.domain.dto.OrderDto;
import com.switchfully.eurder.mapper.ItemMapper;
import com.switchfully.eurder.mapper.OrderMapper;
import com.switchfully.eurder.repository.ItemRepository;
import com.switchfully.eurder.repository.OrderRepository;
import com.switchfully.eurder.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureTestDatabase
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

	User user;
	Order order;
	Item item;

	@BeforeEach
	void init(){
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
	void whenCreateOrder_thenPopulateRepository(){
		userRepository.save(user);
		orderService.createOrder(user.getUserId());

		assertThat(orderRepository.findAll().size()).isNotEqualTo(0);
	}

	@Test
	void whenAddItemToOrder_thenReturnOrderDtoAndCallItemRepositoryOrderRepositoryAndOrderMapper(){
		itemRepository.save(item);
		userRepository.save(user);
		orderRepository.save(order);

		OrderDto actual = orderService.addItemToOrder(order.getOrderId(), item.getItemId(), 3);

		assertThat(actual).isEqualTo(orderMapper.mapOrderToOrderDto(order));
	}
}