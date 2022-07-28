package com.ait.ems.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ait.ems.entity.EmployeeEntity;
import com.ait.ems.model.EmployeeRequest;

public interface EmployeeService {
	
	boolean addEmployee(EmployeeRequest emp);
	boolean editEmployee(EmployeeRequest emp);
	boolean deleteEmployee(Integer empno);
	Page<EmployeeEntity> fetchEmployees(Integer pageNumber);
	EmployeeRequest fetchEmployeeById(Integer empno);

}
