package com.virtusa.employeeapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.employeeapi.entities.Emp;
import com.virtusa.employeeapi.services.EmployeeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@ApiOperation(value = "Retrieving Employees",notes = "Getting employees from db")
	@PreAuthorize("hasAuthority('USER')")
	@GetMapping
	public ResponseEntity<List<Emp>> getEmployees()
	{
		return new ResponseEntity<>(employeeService.getEmployees(),HttpStatus.OK);
	}
	@ApiOperation(value = "Retrieving Employee",notes = "Getting employee based upon employee no")
	@GetMapping("/{eno}")
	public ResponseEntity<Emp> getEmployee(@PathVariable("eno") int eno){
		return new ResponseEntity<Emp>(employeeService.getEmployee(eno), HttpStatus.OK);
	}
	@ApiOperation(value = "Retrieving Employees paged",notes = "Getting employees page by Page")
	@GetMapping("/{pageNo}/{noOfPages}")
	public ResponseEntity<List<Emp>> getEmployeesPaged(int pageNo,int noOfPages){
		return new ResponseEntity<List<Emp>>(employeeService.getEmployeesWithPaged(pageNo, noOfPages), HttpStatus.OK);
	}
	@ApiOperation(value = "Inserting employee",notes = "Inserting employee into db")
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Emp> insertEmployee(@RequestBody Emp e){
		return new ResponseEntity<Emp>(employeeService.insertEmployee(e),HttpStatus.CREATED);
	}
	@ApiOperation(value = "Deleting Employee",notes = "Deleting employee based upon employee no")
	@DeleteMapping("/{eno}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("eno") int eno){
		employeeService.deleteEmployee(eno);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
