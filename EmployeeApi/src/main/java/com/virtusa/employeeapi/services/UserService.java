package com.virtusa.employeeapi.services;

import java.util.List;

import com.virtusa.employeeapi.entities.User;
import com.virtusa.employeeapi.model.UserRequest;

public interface UserService {
	void registerUser(UserRequest userRequest);
	List<User> getUsers();	
}
