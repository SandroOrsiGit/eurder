package com.switchfully.eurder.domain.dto;

import com.switchfully.eurder.domain.Address;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CreateUserDto {

	@NotNull
	@NotEmpty
	private final String firstName;
	@NotNull
	@NotEmpty
	private final String lastName;
	@NotNull
	@NotEmpty
	private final String email;
	@NotNull
	private final Address address;
	@NotNull
	@NotEmpty
	private final String phoneNumber;

	public CreateUserDto(String firstName, String lastName, String email, Address address, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
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
