package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.User;
import com.switchfully.eurder.domain.dto.CreateUserDto;
import com.switchfully.eurder.domain.dto.UserDto;
import com.switchfully.eurder.exceptions.IdNotFoundException;
import com.switchfully.eurder.mapper.UserMapper;
import com.switchfully.eurder.repository.UserRepository;
import jakarta.persistence.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

	UserRepository userRepository;
	UserMapper userMapper;

	public UserService(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	public UserDto createUser(CreateUserDto createUserDto) {
		User user = userMapper.mapCreateUserDtoToUser(createUserDto);
		userRepository.save(user);

		return userMapper.mapUserToUserDto(user);
	}

	public List<UserDto> getUsers() {
		return userRepository.findAll()
				.stream()
				.map(user -> userMapper.mapUserToUserDto(user))
				.collect(Collectors.toList());
	}

	public UserDto getUserById(Long userId) {
		return userMapper.mapUserToUserDto(
				userRepository
						.findUserByUserId(userId)
						.orElseThrow(() -> new IdNotFoundException("User with id " + userId + " not found")));
	}
}
