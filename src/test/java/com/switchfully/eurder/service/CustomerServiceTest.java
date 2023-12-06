package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.dto.CreateCustomerDto;
import com.switchfully.eurder.mapper.CustomerMapper;
import com.switchfully.eurder.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CustomerServiceTest {
	CustomerRepository customerRepository;
	CustomerMapper customerMapper;
	CustomerService customerService;
	CreateCustomerDto createCustomerDto;

	@BeforeEach
	void init(){
		customerRepository = mock(CustomerRepository.class);
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
	}

	@Test
	void whenCreateCustomer_thenCallRepositoryAndMapper(){
		customerService.createCustomer(createCustomerDto);

		verify(customerMapper).mapCreateCustomerDtoToCustomer(createCustomerDto);
		verify(customerRepository).createCustomer(customerMapper.mapCreateCustomerDtoToCustomer(createCustomerDto));
	}

}