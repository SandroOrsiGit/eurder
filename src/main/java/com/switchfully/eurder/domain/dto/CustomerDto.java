package com.switchfully.eurder.domain.dto;

import com.switchfully.eurder.domain.Address;

import java.util.UUID;

public class CustomerDto {
	private UUID customerId;
	private String firstName;
	private String lastName;
	private String email;
	private Address Address;
	private String phoneNumber;

	public CustomerDto(UUID customerId, String firstName, String lastName, String email, com.switchfully.eurder.domain.Address address, String phoneNumber) {
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		Address = address;
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

	public com.switchfully.eurder.domain.Address getAddress() {
		return Address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
}
