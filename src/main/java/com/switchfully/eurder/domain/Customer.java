package com.switchfully.eurder.domain;

public class Customer extends User {
	public Customer(String firstName, String lastName, String email, Address address, String phoneNumber) {
		super(firstName, lastName, email, address, phoneNumber);
	}
}
