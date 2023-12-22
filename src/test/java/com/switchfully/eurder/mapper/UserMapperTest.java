package com.switchfully.eurder.mapper;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.User;
import com.switchfully.eurder.domain.dto.CreateUserDto;
import com.switchfully.eurder.domain.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {

	UserMapper userMapper;
	CreateUserDto createUserDto;
	User user;

	@BeforeEach
	void init(){
		userMapper = new UserMapper();
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
	void whenMapCreateCustomerDtoToCustomer_thenReturnCustomer(){
		User actual = userMapper.mapCreateUserDtoToUser(createUserDto);

		assertThat(actual).isNotNull().isInstanceOf(User.class);
	}

	@Test
	void whenMapCustomerToCustomerDto_thenReturnCustomerDto(){
		UserDto actual = userMapper.mapUserToUserDto(user);

		assertThat(actual).isNotNull().isInstanceOf(UserDto.class);
	}
}