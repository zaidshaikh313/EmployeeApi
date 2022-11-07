package com.virtusa.employeeapi.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.virtusa.employeeapi.entities.User;
import com.virtusa.employeeapi.entities.UserRole;
import com.virtusa.employeeapi.exceptions.UserAllReadyExistsException;
import com.virtusa.employeeapi.model.UserRequest;
import com.virtusa.employeeapi.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService,UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findByUserName(username).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
		List<UserRole> userRoles=user.getRoles().stream().collect(Collectors.toList());
		List<GrantedAuthority> authorities=userRoles.stream().map(r->{
			SimpleGrantedAuthority authority=new SimpleGrantedAuthority(r.getRoleName());
			return authority;
		}).collect(Collectors.toList());
		
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(), authorities);
	}
	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	@Override
	public void registerUser(UserRequest userRequest) {
		if(userRepository.findByUserName(userRequest.getUsername()).isPresent()) {
			throw new UserAllReadyExistsException("User with same username allready existed");
		}
		User user=new User();
		user.setUserName(userRequest.getUsername());
		user.setUserPassword(passwordEncoder.encode(userRequest.getPassword()));
		user.setRoles(userRequest.getRoles().stream().map(r->{
			UserRole role=new UserRole();
			role.setRoleName(r);
			role.setUser(user);
			return role;
		}).collect(Collectors.toSet()));
		userRepository.save(user);
	}

}
