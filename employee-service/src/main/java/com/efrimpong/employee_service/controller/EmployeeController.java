package com.efrimpong.employee_service.controller;

import com.efrimpong.employee_service.model.Employee;
import com.efrimpong.employee_service.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
	
	@PostMapping
	public Employee addEmployee(@RequestBody  Employee employee) {
		LOGGER.info("Adding employee : {}", employee);
		return employeeRepository.save(employee);
	}
	
	@GetMapping
	public List<Employee> getAllEmployees() {
		LOGGER.info("Getting all employees");
		return employeeRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Employee findById(@PathVariable Long id) {
		LOGGER.info("Finding employee by id : {}", id);
		return employeeRepository.findById(id);
	}
	
	@GetMapping("/department/{departmentId}")
	public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId) {
		LOGGER.info("Finding employee by department id : {}", departmentId);
		return employeeRepository.findByDepartment(departmentId);
	}
}
