package com.switchfully.eurder.api;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.dto.CreateCustomerDto;
import com.switchfully.eurder.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CustomerControllerTest {
	CustomerService customerService;
	CustomerController customerController;
	CreateCustomerDto createCustomerDto;

	@BeforeEach
	void init(){
		customerService = mock(CustomerService.class);
		customerController = new CustomerController(customerService);
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
	void whenCreateCustomer_thenCallService(){
		customerController.createCustomer(createCustomerDto);

		verify(customerService).createCustomer(createCustomerDto);
	}

	@Test
	void whenGetCustomers_thenCallService(){
		customerController.getCustomers();

		verify(customerService).getCustomers();
	}

	@Test
	void whenGetCustomerById_thenCallService(){
		UUID uuid = UUID.randomUUID();
		customerController.getCustomerById(uuid);

		verify(customerService).getCustomerById(uuid);
	}
}