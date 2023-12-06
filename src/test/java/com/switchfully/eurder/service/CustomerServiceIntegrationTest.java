package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.dto.CreateCustomerDto;
import com.switchfully.eurder.domain.dto.CustomerDto;
import com.switchfully.eurder.mapper.CustomerMapper;
import com.switchfully.eurder.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceIntegrationTest {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	CustomerMapper customerMapper;

	@Autowired
	CustomerService customerService;

	CreateCustomerDto createCustomerDto;

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
}