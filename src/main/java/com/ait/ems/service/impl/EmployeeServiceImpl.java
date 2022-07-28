package com.ait.ems.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.ait.ems.entity.EmployeeEntity;
import com.ait.ems.model.EmployeeRequest;
import com.ait.ems.repository.EmployeeRepository;
import com.ait.ems.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository repo; 

	//@PreAuthorize(value="(hasRole('ROLE_MANAGER') && authentication.name=='shekher')")
	@Override
	public boolean addEmployee(EmployeeRequest emp) {
		if(!repo.existsById(emp.getEmpno())) {
			EmployeeEntity entity = new EmployeeEntity();
			BeanUtils.copyProperties(emp, entity);
			repo.save(entity);
			return true;
		}
		return false;
	}

	@Override
	public boolean editEmployee(EmployeeRequest emp) {
		EmployeeEntity entity = new EmployeeEntity();
		BeanUtils.copyProperties(emp, entity);
		repo.saveAndFlush(entity);
		return true;
	}

	@Override
	public boolean deleteEmployee(Integer empno) {
		repo.deleteById(empno);
		return true;
	}

	@Override
	public Page<EmployeeEntity> fetchEmployees(Integer pageNumber) {
		
		Pageable pageable = PageRequest.of(pageNumber, 4);
		return repo.findAll(pageable);
		/*
		List<EmployeeRequest> lst = new ArrayList<>();
		
		List<EmployeeEntity> entitiesLst = repo.findAll();
		
		entitiesLst.forEach(entity -> {
			EmployeeRequest emp = new EmployeeRequest();
			BeanUtils.copyProperties(entity, emp);
			lst.add(emp);
		});
		return lst;
		*/
	}
	
	@Override
	public EmployeeRequest fetchEmployeeById(Integer empno) {
		EmployeeEntity  entity = repo.findById(empno).get();
		EmployeeRequest emp = new EmployeeRequest();
		BeanUtils.copyProperties(entity, emp);
		return emp;
	}

}
