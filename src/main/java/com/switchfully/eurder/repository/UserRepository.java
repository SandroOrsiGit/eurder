package com.switchfully.eurder.repository;

import com.switchfully.eurder.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class CustomerRepository {

	private final Map<UUID, Customer> customers;

	public CustomerRepository() {
		customers = new HashMap<>();
	}

	public Map<UUID, Customer> getCustomers() {
		return customers;
	}

	public void createCustomer(Customer customer) {
		customers.put(customer.getCustomerId(), customer);
	}
}
