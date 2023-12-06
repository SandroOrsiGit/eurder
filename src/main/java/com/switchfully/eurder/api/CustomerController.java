package com.switchfully.eurder.api;

import com.switchfully.eurder.domain.dto.CreateCustomerDto;
import com.switchfully.eurder.domain.dto.CustomerDto;
import com.switchfully.eurder.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequestMapping(path = "api/customer")
public class CustomerController {

	CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = "application/json", produces = "application/json")
	public CustomerDto createCustomer(@Valid @RequestBody CreateCustomerDto createCustomerDto) {
		return customerService.createCustomer(createCustomerDto);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = "application/json")
	public List<CustomerDto> getCustomers(){
		return customerService.getCustomers();
	}
}
