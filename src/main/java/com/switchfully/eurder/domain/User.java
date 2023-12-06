package com.switchfully.eurder.domain;

import com.switchfully.eurder.domain.Address;
import org.springframework.lang.NonNull;

import java.util.UUID;

public abstract class User {
	private final UUID customerId;
	private final String firstName;
	private final String lastName;
	private final String email;
	private final Address address;
	private final String phoneNumber;

	public User(String firstName, String lastName, String email, Address address, String phoneNumber) {
		this.customerId = UUID.randomUUID();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public UUID getCustomerId() {
		return customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public Address getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
}
