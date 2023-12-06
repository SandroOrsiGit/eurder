package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.Customer;
import com.switchfully.eurder.domain.dto.CreateCustomerDto;
import com.switchfully.eurder.domain.dto.CustomerDto;
import com.switchfully.eurder.mapper.CustomerMapper;
import com.switchfully.eurder.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CustomerServiceIntegrationTest {

	@Autowired
	private UserRepository customerRepository;

	@Autowired
	CustomerMapper customerMapper;

	@Autowired
	CustomerService customerService;

	CreateCustomerDto createCustomerDto;
	Customer customer;

	@BeforeEach
	void init(){
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
	void whenCreateCustomer_thenReturnCustomerDtoAndPopulateRepository(){
		CustomerDto actual = customerService.createCustomer(createCustomerDto);

		assertThat(customerRepository.getCustomers().size()).isNotEqualTo(0);

		assertThat(actual.getFirstName()).isEqualTo(createCustomerDto.getFirstName());
		assertThat(actual.getLastName()).isEqualTo(createCustomerDto.getLastName());
		assertThat(actual.getEmail()).isEqualTo(createCustomerDto.getEmail());
		assertThat(actual.getAddress()).isEqualTo(createCustomerDto.getAddress());
		assertThat(actual.getPhoneNumber()).isEqualTo(createCustomerDto.getPhoneNumber());
	}

	@Test
	void whenGetCustomers_thenReturnListOfCustomerDto(){
		customerRepository.createCustomer(customer);

		List<CustomerDto> actual = customerService.getCustomers();

		assertThat(actual).contains(customerMapper.mapCustomerToCustomerDto(customer));
	}

	@Test
	void whenGetCustomerById_thenReturnCustomer(){
		customerRepository.createCustomer(customer);
		CustomerDto actual = customerService.getCustomerById(customer.getCustomerId());

		assertThat(actual).isEqualTo(customerMapper.mapCustomerToCustomerDto(customer));
	}
}