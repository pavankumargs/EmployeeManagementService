package com.pavan.microservices.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pavan.microservices.employee.dto.EmployeeResponseDTO;
import com.pavan.microservices.employee.entity.Employee;
import com.pavan.microservices.employee.exception.EmployeeNotFoundException;
import com.pavan.microservices.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public EmployeeResponseDTO getEmployeeById(Long id) {

		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee ID " + id + " Not Found"));

		EmployeeResponseDTO employeeDTO = new EmployeeResponseDTO();

		employeeDTO.setEmployeeId(employee.getEmployeeId());
		employeeDTO.setEmployeeName(employee.getEmployeeName());
		employeeDTO.setEmail(employee.getEmail());
		employeeDTO.setDepartmentId(employee.getDepartmentId());
		return employeeDTO;
	}

	public List<EmployeeResponseDTO> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();

		List<EmployeeResponseDTO> emplist = new ArrayList<>();

		for (Employee emp : employees) {
			EmployeeResponseDTO employeeDTO = new EmployeeResponseDTO();
			employeeDTO.setEmployeeId(emp.getEmployeeId());
			employeeDTO.setEmployeeName(emp.getEmployeeName());
			employeeDTO.setEmail(emp.getEmail());
			employeeDTO.setDepartmentId(emp.getDepartmentId());
			emplist.add(employeeDTO);
		}
		return emplist;
	}

	public Employee updateEmployee(Long id, Employee employee) {
		Employee existingEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee ID " + id + " Not Found"));
		if (employee.getEmployeeName() != null) {
			existingEmployee.setEmployeeName(employee.getEmployeeName());
		}
		if (employee.getEmail() != null) {
			existingEmployee.setEmail(employee.getEmail());
		}
		if (employee.getSalary() != null) {
			existingEmployee.setSalary(employee.getSalary());
		}
		if (employee.getEmployeeCode() != null) {
			existingEmployee.setEmployeeCode(employee.getEmployeeCode());
		}
		if (employee.getJoiningDate() != null) {
			existingEmployee.setJoiningDate(employee.getJoiningDate());
		}
		if (employee.getPhoneNumber() != null) {
			existingEmployee.setPhoneNumber(employee.getPhoneNumber());
		}
		if (employee.getDesignation() != null) {
			existingEmployee.setDesignation(employee.getDesignation());
		}
		if (employee.getStatus() != null) {
			existingEmployee.setStatus(employee.getStatus());
		}
		if (employee.getDepartmentId() != null) {
			existingEmployee.setDepartmentId(employee.getDepartmentId());
		}
		return employeeRepository.save(existingEmployee);
	}

	public void deleteEmployeeById(Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee ID " + id + " Not Found"));
		employeeRepository.delete(employee);
	}
}
