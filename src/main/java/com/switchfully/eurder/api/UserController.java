package com.switchfully.eurder.api;

import com.switchfully.eurder.domain.dto.CreateUserDto;
import com.switchfully.eurder.domain.dto.UserDto;
import com.switchfully.eurder.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequestMapping(path = "api/user")
public class UserController {

	UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = "application/json", produces = "application/json")
	public UserDto createUser(@Valid @RequestBody CreateUserDto createUserDto) {
		return userService.createUser(createUserDto);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = "application/json")
	public List<UserDto> getUsers(){
		return userService.getUsers();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/{userId}", produces = "application/json")
	public UserDto getUserById(@PathVariable Long userId){
		return userService.getUserById(userId);
	}
}
