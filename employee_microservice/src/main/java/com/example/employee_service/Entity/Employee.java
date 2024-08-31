package com.example.employee_service.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employee")
@Setter
@Getter
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	int id;
	
	@Column(name="Name")
	String name;
	
	@Column(name="Department")
	String department;
	
	@Column(name="Email")
	String email;
	
	@Column(name="Salary")
	String salary;
	
	@Column(name="Phone")
	String phone;
		
	public Employee() {	}
	
	public Employee(String Name, String Department, String emailId, String salary, String phone) {
		super();
		this.name = Name;
		this.department = Department;
		this.email = emailId;
		this.salary = salary;
		this.phone = phone;
	}
}
