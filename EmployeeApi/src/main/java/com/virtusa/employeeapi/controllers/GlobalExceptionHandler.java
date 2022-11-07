package com.virtusa.employeeapi.controllers;

import java.time.LocalDate;

import javax.naming.ServiceUnavailableException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.virtusa.employeeapi.exceptions.EmployeeNotFoundException;
import com.virtusa.employeeapi.model.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(EmployeeNotFoundException ex) {
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setErrorMessage(ex.getLocalizedMessage());
		errorResponse.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
		errorResponse.setLocalDate(LocalDate.now());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
	}
	@ExceptionHandler(ServiceUnavailableException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(ServiceUnavailableException ex) {
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setErrorMessage(ex.getLocalizedMessage());
		errorResponse.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
		errorResponse.setLocalDate(LocalDate.now());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
	}
}
