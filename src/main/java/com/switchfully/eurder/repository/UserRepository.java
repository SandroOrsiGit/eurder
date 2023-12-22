package com.switchfully.eurder.repository;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.User;
import com.switchfully.eurder.exceptions.IdNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAll();

	Optional<User> findUserByUserId(Long userId);


}
