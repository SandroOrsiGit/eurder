package com.switchfully.eurder.repository;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.Customer;
import com.switchfully.eurder.exceptions.IdNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

	@Test
	void whenGetCustomerById_thenReturnCustomer(){
		userRepository.createCustomer(customer);

		Customer actual = userRepository.getCustomerById(customer.getCustomerId());

		assertThat(actual).isEqualTo(customer);
	}
	@Test
	void givenInvalidId_whenGetCustomerById_thenThrowIdNotFoundException(){
		assertThatThrownBy(() -> userRepository.getCustomerById(customer.getCustomerId()))
				.isInstanceOf(IdNotFoundException.class)
				.hasMessageContaining("The specified customer ID could not be found");
	}

}