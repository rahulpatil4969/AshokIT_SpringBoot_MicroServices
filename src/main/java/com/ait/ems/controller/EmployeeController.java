package com.ait.ems.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ait.ems.entity.EmployeeEntity;
import com.ait.ems.model.EmployeeRequest;
import com.ait.ems.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService service;
	
	@GetMapping("/logoutUser")
	public String userLoggedOut() {
		return "index";
	}
	
	@GetMapping("/index")
	public String  getIndexPage() {
		return  "index";
	}
	
	@GetMapping("/addEmployee")
	public String getAddEmployeePage(Model model) {
		
		EmployeeRequest  employeeRequest=new EmployeeRequest();
		model.addAttribute("employeeRequest", employeeRequest);
		
		return "addEmployee";
	}
	
	@PostMapping("/saveEmployee")
	public String  saveEmployee(@ModelAttribute("employeeRequest") EmployeeRequest  employeeRequest, Model model) {
		
		boolean flag=service.addEmployee(employeeRequest);
		if(flag==true) {
			
			model.addAttribute("message", "Employee is added to Database");
		}
		else {
			model.addAttribute("message", "Employee already exist!!!");
		}
		
		return "index";
	}
	
	@GetMapping("/listEmployees")
	public String listEmployees(@RequestParam(value="pageNumber", defaultValue = "0")Integer pageNumber,Model model) {
		
		Page<EmployeeEntity> page = service.fetchEmployees(pageNumber);
		
		List<EmployeeEntity> entitiesLst = page.getContent();
		
		List<EmployeeRequest> lst = new ArrayList<>();
		entitiesLst.forEach(entity -> {
			EmployeeRequest emp = new EmployeeRequest();
			BeanUtils.copyProperties(entity, emp);
			lst.add(emp);
		});
		
		model.addAttribute("employees", lst);
		model.addAttribute("isNext", page.hasNext());
		model.addAttribute("isPrevious", page.hasPrevious());
		model.addAttribute("currentPage", page.getNumber());
		return "list_employees";
		
	}
	
	@GetMapping("/editEmployee")
	public String editEmployee(@RequestParam Integer id, Model model) {
		EmployeeRequest emp = service.fetchEmployeeById(id);
		model.addAttribute("emp", emp);
		return "edit_employee";
	}
	
	@PostMapping("/updateEmployee")
	public String updateEmployee(@ModelAttribute EmployeeRequest emp) {
		service.editEmployee(emp);
		return "redirect:listEmployees";
	}
	
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam Integer id) {
		service.deleteEmployee(id);
		return "redirect:listEmployees";
	}

}
