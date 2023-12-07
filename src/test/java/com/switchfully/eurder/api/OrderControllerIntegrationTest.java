package com.switchfully.eurder.api;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.Customer;
import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.domain.Order;
import com.switchfully.eurder.domain.dto.ItemDto;
import com.switchfully.eurder.domain.dto.OrderDto;
import com.switchfully.eurder.mapper.ItemMapper;
import com.switchfully.eurder.repository.ItemRepository;
import com.switchfully.eurder.repository.OrderRepository;
import com.switchfully.eurder.service.ItemService;
import com.switchfully.eurder.service.OrderService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerIntegrationTest {
	@LocalServerPort
	private int port;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderController orderController;

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
	void whenCreateOrder_thenReturnCreatedAndPopulateRepository(){
		OrderDto result = given()
				.contentType(ContentType.JSON)
				.baseUri("http://localhost")
				.port(port)
				.when()
				.queryParam("customerId", customer.getCustomerId())
				.post("/api/order")
				.then()
				.assertThat()
				.statusCode(201)
				.extract()
				.as(OrderDto.class);

		assertThat(orderRepository.getOrders()).containsKey(result.getOrderId());
	}
}