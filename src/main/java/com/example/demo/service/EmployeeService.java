package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ApiEmployeeResponse;
import com.example.demo.dto.ApiListResponse;
import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.DeleteEmployeeRequest;
import com.example.demo.dto.UpdateEmployee;
import com.example.demo.entity.Employee;

@Service
public interface EmployeeService {

	public ApiResponse addEmployee(Employee employee);
	
	public ApiListResponse listOfEmployees();
	
	public ApiListResponse listofFilteredEmployee(String filter);
	
	public ApiEmployeeResponse findEmployeeDetail(Long id);
	
	public boolean updateEmployee(UpdateEmployee employee, Long id);
	
	public boolean deleteEmployee(DeleteEmployeeRequest employee, Long id);
}
