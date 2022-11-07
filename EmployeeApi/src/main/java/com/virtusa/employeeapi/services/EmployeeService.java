package com.virtusa.employeeapi.services;

import java.time.LocalDate;
import java.util.List;

import com.virtusa.employeeapi.entities.Emp;

public interface EmployeeService {
	List<Emp> getEmployees();
	Emp getEmployee(int eno);
	List<Emp> getEmployeesWithJoinDate(LocalDate doj);
	List<Emp> getEmployeesWithPaged(int pageNo,int noOfPages);
	void deleteEmployee(int eno);
	List<Emp> getEmployeesLocatedInAddress(String address);
	List<Emp> getEmployeesWithPagedAndSortedByEno(int pageNo,int noOfPages);
	Emp insertEmployee(Emp e);
}
