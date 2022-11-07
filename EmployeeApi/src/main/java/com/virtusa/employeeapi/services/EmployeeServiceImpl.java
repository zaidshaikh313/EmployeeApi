package com.virtusa.employeeapi.services;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.virtusa.employeeapi.entities.Emp;
import com.virtusa.employeeapi.exceptions.EmployeeNotFoundException;
import com.virtusa.employeeapi.repositories.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Override
	public List<Emp> getEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Emp getEmployee(int eno) {
		return employeeRepository.findById(eno).orElseThrow(()->new EmployeeNotFoundException("Employee not found"));
	}

	@Override
	public List<Emp> getEmployeesWithJoinDate(LocalDate doj) {
		Emp e=new Emp();
		e.setDateOfJoin(doj);
		ExampleMatcher exampleMatcher=ExampleMatcher.matching()
				.withMatcher("dateOfJoin", ExampleMatcher.GenericPropertyMatchers.exact())
				.withIgnorePaths("eno","name","address");
		Example<Emp> example=Example.of(e,exampleMatcher);
		return employeeRepository.findAll(example);
	}

	@Override
	public List<Emp> getEmployeesWithPaged(int pageNo, int noOfPages) {
		Pageable pageable=PageRequest.of(pageNo,noOfPages);
		Page<Emp> page=employeeRepository.findAll(pageable);
		return page.getContent();
	}

	@Override
	public void deleteEmployee(int eno) {
		employeeRepository.deleteById(eno);
	}

	@Override
	public List<Emp> getEmployeesLocatedInAddress(String address) {
		Emp e=new Emp();
		e.setAddress(address);
		ExampleMatcher exampleMatcher=ExampleMatcher.matching()
				.withMatcher("address", ExampleMatcher.GenericPropertyMatchers.exact())
				.withIgnorePaths("eno","name","dateOfJoin");
		Example<Emp> example=Example.of(e, exampleMatcher);
		return employeeRepository.findAll(example);
	}

	@Override
	public List<Emp> getEmployeesWithPagedAndSortedByEno(int pageNo, int noOfPages) {
		Pageable pageable=PageRequest.of(pageNo, noOfPages, Sort.by(Direction.DESC, "eno"));
		Page<Emp> page=employeeRepository.findAll(pageable);
		return page.getContent();
	}

	@Override
	public Emp insertEmployee(Emp e) {
		return employeeRepository.save(e);
	}

}
