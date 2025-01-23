package com.efrimpong.department_service.model;

import java.util.ArrayList;
import java.util.List;

public class Department {
	List<Employee> employees = new ArrayList<Employee>();
	private Long id;
	private String name;
	
	public Department() {
	}
	
	public Department(List<Employee> employees, Long id, String name) {
		this.employees = employees;
		this.id = id;
		this.name = name;
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}
	
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Department{" +
				"employees=" + employees +
				", id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
