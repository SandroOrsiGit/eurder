package com.switchfully.eurder.mapper;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.Customer;
import com.switchfully.eurder.domain.dto.CreateCustomerDto;
import com.switchfully.eurder.domain.dto.CustomerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

	CustomerMapper customerMapper;
	CreateCustomerDto createCustomerDto;
	Customer customer;

	@BeforeEach
	void init(){
		customerMapper = new CustomerMapper();
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
	void whenMapCreateCustomerDtoToCustomer_thenReturnCustomer(){
		Customer actual = customerMapper.mapCreateCustomerDtoToCustomer(createCustomerDto);

		assertThat(actual).isNotNull().isInstanceOf(Customer.class);
	}

	@Test
	void whenMapCustomerToCustomerDto_thenReturnCustomerDto(){
		CustomerDto actual = customerMapper.mapCustomerToCustomerDto(customer);

		assertThat(actual).isNotNull().isInstanceOf(CustomerDto.class);
	}
}