package com.virtusa.employeeapi.exceptions;

public class UserAllReadyExistsException extends RuntimeException {
	public UserAllReadyExistsException(String desc) {
		super(desc);
	}
}	
