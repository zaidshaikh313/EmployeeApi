package com.virtusa.employeeapi.controllers;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.employeeapi.exceptions.InvalidUserCreadentialsException;
import com.virtusa.employeeapi.exceptions.UserDisabledException;
import com.virtusa.employeeapi.model.UserRequest;
import com.virtusa.employeeapi.model.UserResponse;
import com.virtusa.employeeapi.services.UserService;
import com.virtusa.employeeapi.util.JwtUtil;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;
	@PostMapping("/signUp")
	public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest){
		userService.registerUser(userRequest);
		return new ResponseEntity<String>("User Successfully Registered", HttpStatus.OK);
	}
	@PostMapping("/signIn")
	public ResponseEntity<UserResponse> signIn(@RequestBody UserRequest userRequest) throws InvalidUserCreadentialsException{
		Authentication authentication=null;
		try {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword());
			authentication=authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			
		} catch (DisabledException e) {
			throw new UserDisabledException("User is Disbaled");
		}
		catch(BadCredentialsException e)
		{
			throw new InvalidUserCreadentialsException("Invalid Credentials");
		}
		User user=(User)authentication.getPrincipal();
		Set<String> roles=user.getAuthorities().stream().map(authority->authority.getAuthority()).collect(Collectors.toSet());
		//Generate Token using authentication object
		String token=jwtUtil.generateToken(authentication);
		UserResponse response=new UserResponse();
		response.setToken(token);
		response.setRoles(roles.stream().collect(Collectors.toList()));
		return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
	}
	
}
