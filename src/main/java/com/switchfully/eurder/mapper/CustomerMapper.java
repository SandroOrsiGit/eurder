package com.switchfully.eurder.mapper;

import com.switchfully.eurder.domain.Customer;
import com.switchfully.eurder.domain.dto.CreateCustomerDto;
import com.switchfully.eurder.domain.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;

@Service
public class CustomerMapper {
	public Customer mapCreateCustomerDtoToCustomer(CreateCustomerDto createCustomerDto) {
		return new Customer(createCustomerDto.getFirstName(),
				createCustomerDto.getLastName(),
				createCustomerDto.getEmail(),
				createCustomerDto.getAddress(),
				createCustomerDto.getPhoneNumber());
	}

	public CustomerDto mapCustomerToCustomerDto(Customer customer){
		return new CustomerDto(customer.getCustomerId(),
				customer.getFirstName(),
				customer.getLastName(),
				customer.getEmail(),
				customer.getAddress(),
				customer.getPhoneNumber());
	}
}
