package com.example.employee_service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employee_service.Entity.Employee;
import com.example.employee_service.Exception.ResourceNotFoundException;
import com.example.employee_service.Repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repo;
	
	public static int add(int a, int b) {
		return a+b;
	}

	public Employee createEmployee(Employee employee) {
		repo.save(employee);
		return employee;
	}

	public List<Employee> getAllEmployees() {
		return repo.findAll();
	}

	public Employee getEmployeeById(int id) {
		
		Optional<Employee> employee = repo.findById(id);
		return employee.get();
		
//		return repo.findAll().stream()
//						.filter(employee -> employee.getId() == id)
//						.findFirst()
//						.orElse(null);
	}

	public Employee updateEmployee(int id, Employee updatedEmployee) {
		Optional<Employee> employee = repo.findById(id);
		Employee emp = employee.get();
		
		if (employee.isPresent()) {
			emp.setId(updatedEmployee.getId());
			emp.setName(updatedEmployee.getName());
			emp.setDepartment(updatedEmployee.getDepartment());
			emp.setEmail(updatedEmployee.getEmail());
			emp.setSalary(updatedEmployee.getSalary());
			emp.setPhone(updatedEmployee.getPhone());
		}
		
		repo.save(emp);
		return emp;
	}

	public Boolean deleteEmployee(int id) {
		
//		Employee deletedEmployee = repo.findById(id)
//							.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
//		repo.delete(deletedEmployee);
//		return deletedEmployee;
		
		Optional<Employee> employee = repo.findById(id);
		
		if(employee.isPresent()) {
			repo.delete(employee.get());
			return true;
		}
		else {
			throw new ResourceNotFoundException("Employee not exist with id :" + id);
		}
	}

}
