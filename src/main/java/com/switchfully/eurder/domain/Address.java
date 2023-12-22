package com.switchfully.eurder.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addresses")
public class Address{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;
	private String city;
	@Column(name = "street_name")
	private String streetName;
	@Column(name = "house_number")
	private String houseNumber;
	@Column(name = "postal_code")
	private String postalCode;

	public Address() {
	}

	public Address(String city, String streetName, String houseNumber, String postalCode) {
		this.city = city;
		this.streetName = streetName;
		this.houseNumber = houseNumber;
		this.postalCode = postalCode;
	}

	public Long getAddressId() {
		return addressId;
	}

	public String getCity() {
		return city;
	}

	public String getStreetName() {
		return streetName;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}
}
