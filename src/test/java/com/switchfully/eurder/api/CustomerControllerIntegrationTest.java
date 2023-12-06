package com.switchfully.eurder.api;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.Customer;
import com.switchfully.eurder.domain.dto.CreateCustomerDto;
import com.switchfully.eurder.domain.dto.CustomerDto;
import com.switchfully.eurder.mapper.CustomerMapper;
import com.switchfully.eurder.repository.CustomerRepository;
import com.switchfully.eurder.service.CustomerService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerMapper customerMapper;

	private CreateCustomerDto createCustomerDto;

	@BeforeEach
	void init() {
		createCustomerDto = new CreateCustomerDto(
				"Karel",
				"Polmark",
				"karel@switchfully.be",
				new Address("Brussel",
						"Keizerslaan",
						"155",
						"1000"),
				"0495123456");
	}

	@Test
	void whenCreateCustomer_thenReturnHttpStatusCreated() {
		given()
				.contentType(ContentType.JSON)
				.baseUri("http://localhost")
				.port(port)
				.when()
				.body(createCustomerDto)
				.post("/api/customer")
				.then()
				.assertThat()
				.statusCode(201)
				.extract()
				.as(CustomerDto.class);

		assertThat(customerRepository.getCustomers()).isNotEmpty();
	}
}