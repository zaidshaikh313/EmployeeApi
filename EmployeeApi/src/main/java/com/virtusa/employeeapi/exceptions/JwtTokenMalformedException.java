package com.virtusa.employeeapi.exceptions;

public class JwtTokenMalformedException extends Exception {
	public JwtTokenMalformedException(String desc)
	{
		super(desc);
	}

}
