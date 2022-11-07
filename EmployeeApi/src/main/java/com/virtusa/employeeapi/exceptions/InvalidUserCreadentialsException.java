package com.virtusa.employeeapi.exceptions;

public class InvalidUserCreadentialsException extends Exception {
	public InvalidUserCreadentialsException(String desc) {
		super(desc);
	}
}
