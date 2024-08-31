package com.example.employee_service.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals; 

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.employee_service.Entity.Employee;
import com.example.employee_service.Exception.ResourceNotFoundException;
import com.example.employee_service.Repository.EmployeeRepository;

@SpringBootTest
public class EmployeeServiceTest {

	@InjectMocks
	private EmployeeService employeeService;
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@Test
	public void addTest() {
		int result = EmployeeService.add(2, 3);
		assertEquals(5, result);
	}
	
	@Test
	public void testGetAllEmployees() {
		
		Employee employee1 = new Employee("Mohit", "IT", "mohit@gmail.com", "38000", "7878787878");
		Employee employee2 = new Employee("John", "CSE", "john@gmail.com", "48000", "8787878787");
		
		List<Employee> list = new ArrayList<>();
		list.add(employee1);
		list.add(employee2);
		
		when(employeeRepository.findAll()).thenReturn(list);
		
		List<Employee> employees = employeeService.getAllEmployees();
		
		assertThat(employees).hasSize(2);
		verify(employeeRepository, times(1)).findAll();
	}
	
	@Test
	public void testGetEmployeeById() {
		
		Employee employee1 = new Employee("Rohan", "IT", "r.gmail.com", "58000", "7878787878");
		employee1.setId(1);
			
		Optional<Employee> expected = Optional.ofNullable(employee1);
		
		when(employeeRepository.findById(1)).thenReturn(expected);
		
		Employee actual = employeeService.getEmployeeById(1);
		
		// assertThat(expected.get().getEmail(), actual.getEmail());
		
//		assertThat(actual.getEmail()).isEqualTo(expected.get().getEmail());
		
		assertEquals(expected.get().getEmail(), actual.getEmail());
	}
	
	@Test
	public void testCreateEmployee() {
		
		Employee employee = new Employee("Aditya","CSE","adi@gmail.com","60000","4444433333");
		
		when(employeeRepository.save(employee)).thenReturn(employee);
		
		Employee createdEmployee = employeeService.createEmployee(employee);
		
		assertEquals(employee, createdEmployee);
		verify(employeeRepository).save(employee);
	}
	
	@Test
	public void testUpdateEmployee() {
		
		Employee existingEmployee = new Employee("john","IT","john.gmail.com","40000","5555566666");
		existingEmployee.setId(1);
		
		Employee updatedEmployee = new Employee("John","CSE","john@gmail.com","50000","5555566666");
		updatedEmployee.setId(1);
		
		when(employeeRepository.findById(1)).thenReturn(Optional.of(existingEmployee));
		when(employeeRepository.save(existingEmployee)).thenReturn(updatedEmployee);
		
		Employee result = employeeService.updateEmployee(1, updatedEmployee);
		
		assertEquals(updatedEmployee.getName(), result.getName());
	    assertEquals(updatedEmployee.getDepartment(), result.getDepartment());
	    assertEquals(updatedEmployee.getEmail(), result.getEmail());
	    assertEquals(updatedEmployee.getSalary(), result.getSalary());
	    assertEquals(updatedEmployee.getPhone(), result.getPhone());
	    
		verify(employeeRepository).findById(1);
		verify(employeeRepository).save(existingEmployee);
	}
	
	@Test
	public void testDeleteEmployee() throws Exception{
		
		Employee employee = new Employee("John","CSE","john@gmail.com","50000","5555566666");
		employee.setId(1);
		
		when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
		
		// Optional<Employee> result = employeeService.deleteEmployee(employee);
		Boolean result = employeeService.deleteEmployee(1);
		
		assertTrue(result);
		verify(employeeRepository).delete(employee);
	}
	
}
