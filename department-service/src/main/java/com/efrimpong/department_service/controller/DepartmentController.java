package com.efrimpong.department_service.controller;


import com.efrimpong.department_service.client.EmployeeClient;
import com.efrimpong.department_service.model.Department;
import com.efrimpong.department_service.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/department")
public class DepartmentController {
	private  static  final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	private EmployeeClient employeeClient;
	
	@PostMapping
	public Department add(@RequestBody  Department department) {
		LOGGER.info("Department add: "+department);
		return departmentRepository.addDepartment(department);
	}
	
	
	@GetMapping
	public List<Department> getAll() {
		LOGGER.info("Department getAll");
		return departmentRepository.findAll();
	}
	
	@GetMapping("/with-employees")
	public List<Department> getAllWithEmployees() {
		LOGGER.info("Get Department getAllWithEmployees");
		List<Department> departments = departmentRepository.findAll();
		departments.forEach(department -> department.setEmployees(
				employeeClient.findByDepartment(department.getId())));
		
		return departments;
	}
	@GetMapping("/{id}")
	public Department getById(@PathVariable Long id) {
		LOGGER.info("Department getById: "+id);
		return departmentRepository.findById(id);
	}
	
	
}
