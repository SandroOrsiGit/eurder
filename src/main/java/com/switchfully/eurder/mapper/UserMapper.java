package com.switchfully.eurder.mapper;

import com.switchfully.eurder.domain.User;
import com.switchfully.eurder.domain.dto.CreateUserDto;
import com.switchfully.eurder.domain.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
	public User mapCreateUserDtoToUser(CreateUserDto createUserDto) {
		return new User(createUserDto.getFirstName(),
				createUserDto.getLastName(),
				createUserDto.getEmail(),
				createUserDto.getAddress(),
				createUserDto.getPhoneNumber());
	}

	public UserDto mapUserToUserDto(User user){
		return new UserDto(user.getUserId(),
				user.getFirstName(),
				user.getLastName(),
				user.getEmail(),
				user.getAddress(),
				user.getPhoneNumber());
	}
}
