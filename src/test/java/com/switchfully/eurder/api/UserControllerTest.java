package com.switchfully.eurder.api;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.dto.CreateUserDto;
import com.switchfully.eurder.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UserControllerTest {
	UserService userService;
	UserController userController;
	CreateUserDto createUserDto;

	@BeforeEach
	void init(){
		userService = mock(UserService.class);
		userController = new UserController(userService);
		createUserDto = new CreateUserDto(
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
	void whenCreateCustomer_thenCallService(){
		userController.createUser(createUserDto);

		verify(userService).createUser(createUserDto);
	}

	@Test
	void whenGetCustomers_thenCallService(){
		userController.getUsers();

		verify(userService).getUsers();
	}

	@Test
	void whenGetCustomerById_thenCallService(){
		userController.getUserById(1L);

		verify(userService).getUserById(1L);
	}
}