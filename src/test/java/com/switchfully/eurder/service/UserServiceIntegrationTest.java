package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.User;
import com.switchfully.eurder.domain.dto.CreateUserDto;
import com.switchfully.eurder.domain.dto.UserDto;
import com.switchfully.eurder.mapper.UserMapper;
import com.switchfully.eurder.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
class UserServiceIntegrationTest {

	@Autowired
	private UserRepository customerRepository;

	@Autowired
	UserMapper userMapper;

	@Autowired
	UserService userService;

	CreateUserDto createUserDto;
	User user;

	@BeforeEach
	void init(){
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
	void whenCreateCustomer_thenReturnCustomerDtoAndPopulateRepository(){
		UserDto actual = userService.createUser(createUserDto);

		assertThat(customerRepository.findAll().size()).isNotEqualTo(0);

		assertThat(actual.getFirstName()).isEqualTo(createUserDto.getFirstName());
		assertThat(actual.getLastName()).isEqualTo(createUserDto.getLastName());
		assertThat(actual.getEmail()).isEqualTo(createUserDto.getEmail());
		assertThat(actual.getAddress()).isEqualTo(createUserDto.getAddress());
		assertThat(actual.getPhoneNumber()).isEqualTo(createUserDto.getPhoneNumber());
	}

	@Test
	void whenGetCustomers_thenReturnListOfCustomerDto(){
		customerRepository.save(user);

		List<UserDto> actual = userService.getUsers();

		assertThat(actual).contains(userMapper.mapUserToUserDto(user));
	}

	@Test
	void whenGetCustomerById_thenReturnCustomer(){
		customerRepository.save(user);
		UserDto actual = userService.getUserById(user.getUserId());

		assertThat(actual).isEqualTo(userMapper.mapUserToUserDto(user));
	}
}