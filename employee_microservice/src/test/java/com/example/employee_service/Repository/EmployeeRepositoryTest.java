package com.example.employee_service.Repository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.employee_service.Entity.Employee;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class EmployeeRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void findByIdTest() {
        Employee employee = new Employee("John", "CSE", "john@gmail.com", "50000", "5555566666");
//      employee.setId(1);
        
        employee = entityManager.persistAndFlush(employee);

        Optional<Employee> found = employeeRepository.findById(employee.getId());

        assertTrue(found.isPresent());
        assertEquals(employee.getName(), found.get().getName());
    }
    

    @Test
    public void saveEmployeeTest() {
        Employee employee = new Employee("Jane", "IT", "jane@gmail.com", "60000", "5555567777");
        Employee saved = employeeRepository.save(employee);

        assertNotNull(saved.getId());
        assertEquals("Jane", saved.getName());
    }
    
}


