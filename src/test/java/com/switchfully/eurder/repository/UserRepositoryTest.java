package com.switchfully.eurder.repository;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest {

	UserRepository userRepository;
	Customer customer;

	@BeforeEach
	void init(){
		userRepository = new UserRepository();
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
		userRepository.createCustomer(customer);

		assertThat(userRepository.getCustomers()).contains(customer);
	}

	@Test
	void whenGetCustomers_thenReturnListOfCustomers(){
		List<Customer> actual = userRepository.getCustomers();

		assertThat(actual).isNotNull();
	}

}