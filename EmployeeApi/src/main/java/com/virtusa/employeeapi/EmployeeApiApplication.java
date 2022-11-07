package com.virtusa.employeeapi;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.virtusa.employeeapi.entities.Emp;
import com.virtusa.employeeapi.repositories.EmployeeRepository;

@SpringBootApplication
@EnableJpaAuditing
@EnableTransactionManagement
public class EmployeeApiApplication {
	@Autowired
	private EmployeeRepository employeeRepository;
	public static void main(String[] args) {
		SpringApplication.run(EmployeeApiApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner() {
		CommandLineRunner runner=(String ...s)->{
			Emp e1=new Emp();
			e1.setName("suresh");
			e1.setAddress("chennai");
			e1.setDateOfJoin(LocalDate.of(2022, 01, 1));
			Emp e2=new Emp();
			e2.setName("ram");
			e2.setAddress("chennai");
			e2.setDateOfJoin(LocalDate.of(2010, 01, 1));
			Emp e3=new Emp();
			e3.setName("vamsi");
			e3.setAddress("hyd");
			e3.setDateOfJoin(LocalDate.of(2022, 01, 1));
			Emp e4=new Emp();
			e4.setName("arjun");
			e4.setAddress("chennai");
			e4.setDateOfJoin(LocalDate.of(2008, 02, 10));
			Emp e5=new Emp();
			e5.setName("rama");
			e5.setAddress("hyd");
			e5.setDateOfJoin(LocalDate.of(2022, 01, 1));
			Emp e6=new Emp();
			e6.setName("varun");
			e6.setAddress("hyd");
			e6.setDateOfJoin(LocalDate.of(2022, 04, 1));
			Emp e7=new Emp();
			e7.setName("ananya");
			e7.setAddress("hyd");
			e7.setDateOfJoin(LocalDate.of(2009, 01, 15));
			List<Emp> emploList=List.of(e1, e2, e3, e4, e5, e6, e7);
			employeeRepository.saveAll(emploList);
		};
		return runner;
	}

}
