package com.te.thymeleaf.repository;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.thymeleaf.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

//	public void generateExcel(HttpServletResponse response);



}
