package com.virtusa.employeeapi.exceptions;

public class JwtTokenNotFoundException extends RuntimeException {
	public JwtTokenNotFoundException(String desc) {
		super(desc);
	}
}
