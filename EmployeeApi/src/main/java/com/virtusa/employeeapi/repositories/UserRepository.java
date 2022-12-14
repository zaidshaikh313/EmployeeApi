package com.virtusa.employeeapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.employeeapi.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUserName(String userName);
}
