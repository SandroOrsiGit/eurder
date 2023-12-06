package com.switchfully.eurder.repository;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryTest {

	CustomerRepository customerRepository;
	Customer customer;

	@BeforeEach
	void init(){
		customerRepository = new CustomerRepository();
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
	void whenCreateCustomer_thenAddCustomerToMap(){
		customerRepository.createCustomer(customer);

		assertThat(customerRepository.getCustomers()).contains(entry(customer.getCustomerId(), customer));
	}

}