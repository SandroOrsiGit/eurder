package com.switchfully.eurder.api;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.User;
import com.switchfully.eurder.domain.dto.CreateUserDto;
import com.switchfully.eurder.domain.dto.UserDto;
import com.switchfully.eurder.mapper.UserMapper;
import com.switchfully.eurder.repository.UserRepository;
import com.switchfully.eurder.service.UserService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class UserControllerIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	private CreateUserDto createUserDto;
	private User user;

	@BeforeEach
	void init() {
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
	void whenCreateUser_thenReturnCreatedAndPopulateRepository() {
		given()
				.contentType(ContentType.JSON)
				.baseUri("http://localhost")
				.port(port)
				.when()
				.body(createUserDto)
				.post("/api/user")
				.then()
				.assertThat()
				.statusCode(201);

		assertThat(userRepository.findAll()).isNotEmpty();
	}

	@Test
	void whenGetCustomers_thenReturnOk(){
		userRepository.save(user);

		List<UserDto> result = given()
				.baseUri("http://localhost")
				.port(port)
				.when()
				.get("/api/user")
				.then()
				.assertThat()
				.statusCode(200)
				.extract()
				.body()
				.jsonPath()
				.getList(".", UserDto.class);

		assertThat(result).contains(userMapper.mapUserToUserDto(user));
	}

	@Test
	void whenGetCustomerById_thenReturnOk(){
		userRepository.save(user);

		UserDto result = given()
				.baseUri("http://localhost")
				.port(port)
				.when()
				.get("/api/user/" + user.getUserId())
				.then()
				.assertThat()
				.statusCode(200)
				.extract()
				.as(UserDto.class);

		assertThat(result).isEqualTo(userMapper.mapUserToUserDto(user));
	}
}