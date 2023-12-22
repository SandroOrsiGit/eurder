package com.switchfully.eurder.api;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.domain.Order;
import com.switchfully.eurder.domain.User;
import com.switchfully.eurder.domain.dto.OrderDto;
import com.switchfully.eurder.mapper.OrderMapper;
import com.switchfully.eurder.repository.ItemRepository;
import com.switchfully.eurder.repository.OrderRepository;
import com.switchfully.eurder.repository.UserRepository;
import com.switchfully.eurder.service.OrderService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class OrderControllerIntegrationTest {
	@LocalServerPort
	private int port;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderController orderController;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	private UserRepository userRepository;

	User user;
	Order order;
	Item item;

	@BeforeEach
	void init() {
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

		userRepository.save(user);
		orderRepository.save(order);
		itemRepository.save(item);
	}

	@Test
	void whenCreateOrder_thenReturnCreatedAndPopulateRepository() {
		OrderDto result = given()
				.queryParam("userId", user.getUserId())
				.contentType(ContentType.JSON)
				.baseUri("http://localhost")
				.port(port)
				.when()
				.post("/api/order")
				.then()
				.assertThat()
				.statusCode(201)
				.extract()
				.as(OrderDto.class);

	}

	@Test
	void whenAddItemToOrder_thenReturnOkAndPopulateOrder() {
		OrderDto result = given()
				.queryParam("itemId", item.getItemId())
				.queryParam("amount", 2)
				.contentType(ContentType.JSON)
				.baseUri("http://localhost")
				.port(port)
				.when()
				.put("/api/order/" + order.getOrderId())
				.then()
				.assertThat()
				.statusCode(200)
				.extract()
				.as(OrderDto.class);

		assertThat(result).isEqualTo(orderMapper.mapOrderToOrderDto(orderRepository.findOrderByOrderId(order.getOrderId()).orElse(null)));
	}
}