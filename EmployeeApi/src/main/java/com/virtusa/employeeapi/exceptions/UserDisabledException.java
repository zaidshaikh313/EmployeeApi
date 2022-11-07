package com.virtusa.employeeapi.exceptions;

public class UserDisabledException extends RuntimeException {
	public UserDisabledException(String desc) {
		super(desc);
	}
}
