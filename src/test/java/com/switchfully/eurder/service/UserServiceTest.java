package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.User;
import com.switchfully.eurder.domain.dto.CreateUserDto;
import com.switchfully.eurder.mapper.UserMapper;
import com.switchfully.eurder.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {
	UserRepository customerRepository;
	UserMapper userMapper;
	UserService userService;
	CreateUserDto createUserDto;
	User user;

	@BeforeEach
	void init(){
		customerRepository = mock(UserRepository.class);
		userMapper = mock(UserMapper.class);
		userService = new UserService(customerRepository, userMapper);
		createUserDto = new CreateUserDto(
				"Karel",
				"Polmark",
				"karel@switchfully.be",
				new Address("Brussel",
						"Keizerslaan",
						"155",
						"1000"),
				"0495123456");
		user = new User(
				"Karel",
				"Polmark",
				"karel@switchfully.be",
				new Address("Brussel",
						"Keizerslaan",
						"155",
						"1000"),
				"0495123456");
	}

	@Test
	void whenCreateCustomer_thenCallRepositoryAndMapper(){
		userService.createUser(createUserDto);

		verify(userMapper).mapCreateUserDtoToUser(createUserDto);
		verify(customerRepository).save(userMapper.mapCreateUserDtoToUser(createUserDto));
	}

	@Test
	void whenGetCustomers_thenCallRepositoryAndMapper(){
		when(customerRepository.findAll()).thenReturn(List.of(user));
		userService.getUsers();

		verify(userMapper).mapUserToUserDto(user);
		verify(customerRepository).findAll();
	}

	@Test
	void whenGetCustomerById_thenCallRepositoryAndMapper(){
		when(customerRepository.findUserByUserId(user.getUserId())).thenReturn(Optional.of(user));
		userService.getUserById(user.getUserId());

		verify(userMapper).mapUserToUserDto(user);
		verify(customerRepository).findUserByUserId(user.getUserId());
	}

}