package com.virtusa.employeeapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.employeeapi.entities.Emp;

public interface EmployeeRepository extends JpaRepository<Emp, Integer>{

}
