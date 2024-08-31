package com.example.employee_service.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.example.employee_service.Entity.Employee;
import com.example.employee_service.Repository.EmployeeRepository;
import com.example.employee_service.Service.EmployeeService;

import jakarta.annotation.PostConstruct;


@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	// Get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	// create an employee
	@PostMapping("/employees")
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee){
		System.out.println("Received Employee: " + employee);
		
		Employee emp =  employeeService.createEmployee(employee);
		
		if(emp != null) {
			return new ResponseEntity<Employee>(emp, HttpStatus.CREATED);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	// get an employee
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
		Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
	}
	
	@PutMapping("employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employeeDetails) {
		
		Employee emp = employeeService.updateEmployee(id, employeeDetails);
		
		if(emp != null) {
			return ResponseEntity.ok(emp);
		}
		else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable int id){
		
		Boolean isDeleted = employeeService.deleteEmployee(id);
		
		if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
	}
}





