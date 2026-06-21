package com.pavan.microservices.employee.controller;

import java.util.List;

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

import com.pavan.microservices.employee.dto.EmployeeResponseDTO;
import com.pavan.microservices.employee.entity.Employee;
import com.pavan.microservices.employee.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {
		Employee savedEmployee = employeeService.saveEmployee(employee);
		return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
		EmployeeResponseDTO dto = employeeService.getEmployeeById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
		List<EmployeeResponseDTO> allEmployees = employeeService.getAllEmployees();
		return ResponseEntity.ok(allEmployees);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployeeById(@RequestBody Employee employee, @PathVariable Long id) {
		Employee updatedEmployee = employeeService.updateEmployee(id, employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployeeById(id);
		return ResponseEntity.noContent().build();
	}
}
