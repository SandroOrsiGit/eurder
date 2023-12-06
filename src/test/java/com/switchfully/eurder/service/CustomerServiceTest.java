package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.Customer;
import com.switchfully.eurder.domain.dto.CreateCustomerDto;
import com.switchfully.eurder.mapper.CustomerMapper;
import com.switchfully.eurder.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CustomerServiceTest {
	UserRepository customerRepository;
	CustomerMapper customerMapper;
	CustomerService customerService;
	CreateCustomerDto createCustomerDto;
	Customer customer;

	@BeforeEach
	void init(){
		customerRepository = mock(UserRepository.class);
		customerMapper = mock(CustomerMapper.class);
		customerService = new CustomerService(customerRepository, customerMapper);
		createCustomerDto = new CreateCustomerDto(
				"Karel",
				"Polmark",
				"karel@switchfully.be",
				new Address("Brussel",
						"Keizerslaan",
						"155",
						"1000"),
				"0495123456");
		customer = new Customer(
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
	void whenCreateCustomer_thenCallRepositoryAndMapper(){
		customerService.createCustomer(createCustomerDto);

		verify(customerMapper).mapCreateCustomerDtoToCustomer(createCustomerDto);
		verify(customerRepository).createCustomer(customerMapper.mapCreateCustomerDtoToCustomer(createCustomerDto));
	}

	@Test
	void whenGetCustomers_thenCallRepositoryAndMapper(){
		customerService.getCustomers();
		when(customerRepository.getCustomers()).thenReturn(List.of(customer));

		verify(customerMapper).mapCustomerToCustomerDto(customer);
		verify(customerRepository).getCustomers();
	}

}