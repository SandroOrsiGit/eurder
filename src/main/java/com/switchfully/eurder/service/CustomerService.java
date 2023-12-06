package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.Customer;
import com.switchfully.eurder.domain.dto.CreateCustomerDto;
import com.switchfully.eurder.domain.dto.CustomerDto;
import com.switchfully.eurder.mapper.CustomerMapper;
import com.switchfully.eurder.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerService {

	UserRepository customerRepository;
	CustomerMapper customerMapper;

	public CustomerService(UserRepository customerRepository, CustomerMapper customerMapper) {
		this.customerRepository = customerRepository;
		this.customerMapper = customerMapper;
	}

	public CustomerDto createCustomer(CreateCustomerDto createCustomerDto) {
		Customer customer = customerMapper.mapCreateCustomerDtoToCustomer(createCustomerDto);
		customerRepository.createCustomer(customer);

		return customerMapper.mapCustomerToCustomerDto(customer);
	}

	public List<CustomerDto> getCustomers() {
		return customerRepository.getCustomers()
				.stream()
				.map(customerMapper::mapCustomerToCustomerDto)
				.collect(Collectors.toList());
	}

	public CustomerDto getCustomerById(UUID customerId) {
		return customerMapper.mapCustomerToCustomerDto(customerRepository.getCustomerById(customerId));
	}
}
