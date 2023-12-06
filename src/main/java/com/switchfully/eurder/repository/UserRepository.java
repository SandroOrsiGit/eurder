package com.switchfully.eurder.repository;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.Customer;
import com.switchfully.eurder.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

	private final Map<UUID, User> users;

	public UserRepository() {
		users = new HashMap<>();
		for(int i = 1; i < 6; i++){
			Customer customer = new Customer(
					"customer" + i,
					"Doe",
					"user" + i + "@switchfully.be",
					new Address("Brussel",
							"Keizerslaan",
							"1" + i,
							"1000"),
					"049512345" + i);
			users.put(customer.getCustomerId(), customer);
		}
	}

	public List<Customer> getCustomers() {
		return users.values()
				.stream()
				.filter(user -> user instanceof Customer)
				.map(user -> (Customer) user)
				.collect(Collectors.toList());
	}

	public void createCustomer(Customer customer) {
		users.put(customer.getCustomerId(), customer);
	}
}
