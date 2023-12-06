package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.Customer;
import com.switchfully.eurder.domain.dto.CreateCustomerDto;
import com.switchfully.eurder.domain.dto.CustomerDto;
import com.switchfully.eurder.mapper.CustomerMapper;
import com.switchfully.eurder.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	CustomerRepository customerRepository;
	CustomerMapper customerMapper;

	public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
		this.customerRepository = customerRepository;
		this.customerMapper = customerMapper;
	}

	public CustomerDto createCustomer(CreateCustomerDto createCustomerDto) {
		Customer customer = customerMapper.mapCreateCustomerDtoToCustomer(createCustomerDto);
		customerRepository.createCustomer(customer);

		return customerMapper.mapCustomerToCustomerDto(customer);
	}
}
