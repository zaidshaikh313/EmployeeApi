package com.virtusa.employeeapi.exceptions;

public class EmployeeNotFoundException extends RuntimeException{
	public EmployeeNotFoundException(String desc) {
		super(desc);
	}

}
