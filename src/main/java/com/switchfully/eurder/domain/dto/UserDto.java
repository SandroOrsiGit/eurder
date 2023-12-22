package com.switchfully.eurder.domain.dto;

import com.switchfully.eurder.domain.Address;

import java.util.Objects;
import java.util.UUID;

public class UserDto {
	private Long userId;
	private String firstName;
	private String lastName;
	private String email;
	private Address Address;
	private String phoneNumber;

	public UserDto(Long userId, String firstName, String lastName, String email, com.switchfully.eurder.domain.Address address, String phoneNumber) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		Address = address;
		this.phoneNumber = phoneNumber;
	}

	public Long getUserId() {
		return userId;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserDto userDto = (UserDto) o;
		return Objects.equals(userId, userDto.userId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId);
	}
}
