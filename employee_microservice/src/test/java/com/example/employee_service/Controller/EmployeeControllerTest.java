package com.example.employee_service.Controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.employee_service.Entity.Employee;
import com.example.employee_service.Service.EmployeeService;

@WebMvcTest
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeService employeeService;
	
	@Test
	public void getAllEmployeesTest() throws Exception {
		
		Employee employee1 = new Employee("Rohan", "CSE", "r.gmail.com", "48000", "7777799999");
		Employee employee2 = new Employee("Sanchit", "IT", "s.gmail.com", "50000", "7778899999");
		employee1.setId(1);
		employee2.setId(2);
		
		List<Employee> list = new ArrayList<>();
		list.add(employee1);
		list.add(employee2);
		
		when(employeeService.getAllEmployees()).thenReturn(list);
		
		mockMvc.perform(get("/api/v1/employees"))
							.andExpect(status().isOk())
							.andExpect(content()
//							.json(	"[{'id':1,'name':'Rohan','department':'CSE','email':'r.gmail.com','salary':'48000','phone':'7777799999'}," +
//									"{'id':2,'name':'Sanchit','department':'IT','email':'s.gmail.com','salary':'50000','phone':'7778899999'}]" ));
									
									
							.json(  "[{\"id\":1,\"name\":\"Rohan\",\"department\":\"CSE\",\"email\":\"r.gmail.com\",\"salary\":\"48000\",\"phone\":\"7777799999\"}," +
				                    "{\"id\":2,\"name\":\"Sanchit\",\"department\":\"IT\",\"email\":\"s.gmail.com\",\"salary\":\"50000\",\"phone\":\"7778899999\"}]" ));              
		
	}
	
	@Test
	public void createEmployeeTest() throws Exception {
		
		Employee employee = new Employee("John", "CSE","john@gmail.com","50000","5555566666");
		employee.setId(1);
		
		when(employeeService.createEmployee(any(Employee.class))).thenReturn(employee);        
		
		mockMvc.perform(post("/api/v1/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":1,\"name\":\"John\",\"department\":\"CSE\",\"email\":\"john@gmail.com\",\"salary\":\"50000\",\"phone\":\"5555566666\"}"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("John")))
				.andExpect(jsonPath("$.department", is("CSE")))
				.andExpect(jsonPath("$.email", is("john@gmail.com")))
				.andExpect(jsonPath("$.salary", is("50000")))
				.andExpect(jsonPath("$.phone", is("5555566666")));
		
//				.andExpect(content().json("{\"id\":1,\"name\":\"John\",\"department\":\"CSE\",\"email\":\"john@gmail.com\",\"salary\":\"50000\",\"phone\":\"5555566666\"}"));
		
	}
	
	@Test
	public void getEmployeeByIdTest() throws Exception{
		
		Employee employee = new Employee("John", "CSE","john@gmail.com","50000","5555566666");
		employee.setId(1);
		
		when(employeeService.getEmployeeById(1)).thenReturn(employee);
		
		mockMvc.perform(get("/api/v1/employees/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())				
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("John")))
				.andExpect(jsonPath("$.department", is("CSE")))
				.andExpect(jsonPath("$.email", is("john@gmail.com")))
				.andExpect(jsonPath("$.salary", is("50000")))
				.andExpect(jsonPath("$.phone", is("5555566666")));
		
//				.andExpect(content().json("{\"id\":1,\"name\":\"John\",\"department\":\"CSE\",\"email\":\"john@gmail.com\",\"salary\":\"50000\",\"phone\":\"5555566666\"}"));
	}
	
	@Test
	public void updateEmployeeTest() throws Exception{
		
		Employee updatedEmployee = new Employee("John", "CSE","john@gmail.com","50000","5555566666");
		updatedEmployee.setId(1);
		
		when(employeeService.updateEmployee(eq(1), any(Employee.class))).thenReturn(updatedEmployee);
		
		mockMvc.perform(put("/api/v1/employees/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":1,\"name\":\"John\",\"department\":\"CSE\",\"email\":\"john@gmail.com\",\"salary\":\"50000\",\"phone\":\"5555566666\"}")) 
				.andExpect(status().isOk())
		        .andExpect(jsonPath("$.id", is(1)))
		        .andExpect(jsonPath("$.name", is("John")))
		        .andExpect(jsonPath("$.department", is("CSE")))
		        .andExpect(jsonPath("$.email", is("john@gmail.com")))
		        .andExpect(jsonPath("$.salary", is("50000")))
		        .andExpect(jsonPath("$.phone", is("5555566666")));
		
//				.andExpect(content().json("{\"id\":1,\"name\":\"John\",\"department\":\"CSE\",\"email\":\"john@gmail.com\",\"salary\":\"50000\",\"phone\":\"5555566666\"}"));
		
	}
	
	@Test
	public void deleteEmployeeTest() throws Exception {
	
		when(employeeService.deleteEmployee(1)).thenReturn(true);
		
		mockMvc.perform(delete("/api/v1/employees/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	
	}

}



