package com.efrimpong.employee_service.repository;

import com.efrimpong.employee_service.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
	
	private final List<Employee> employees = new ArrayList<Employee>();
	
	public Employee findById(Long id){
		return employees.stream().filter(employee -> employee.id().equals(id)).findFirst().orElseThrow();
	}
	
	public List<Employee> findAll(){
		return employees;
	}
	public Employee save(Employee employee){
		employees.add(employee);
		return employee;
	}
	
	public List<Employee> findByDepartment(Long departmentId){
		return employees.stream().filter(e -> e.departmentId().equals(departmentId)).toList();
	}
}
