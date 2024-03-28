package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ApiEmployeeResponse;
import com.example.demo.dto.ApiListResponse;
import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.DeleteEmployeeRequest;
import com.example.demo.dto.EmployeeDetails;
import com.example.demo.dto.UpdateEmployee;
import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeShadow;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeShadowRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private EmployeeShadowRepository employeeShadowRepository;
	
	@Override
	public ApiResponse addEmployee(Employee employee) {
		try {
			Employee e = employeeRepository.save(employee);
			return new ApiResponse("success", null, null, e);
		}catch(Exception e){
			String errorCode = "ADD_EMPLOYEE_ERROR";
			String errorMessage = e.getMessage();
			Employee employe = new Employee();
			return new ApiResponse("error", errorCode, errorMessage, employe);
		}
	}
	
	@Override
	public ApiListResponse listOfEmployees() {
		try {
			List<Employee> empList = employeeRepository.findAll();
			return new ApiListResponse("success", null, null, empList);
		}catch(Exception e) {
			String errorCode = "ADD_EMPLOYEE_ERROR";
			String errorMessage = e.getMessage();
			List<Employee> empList = new ArrayList<>();
			return new ApiListResponse("error", errorCode, errorMessage, empList);
		}
	}
	
	@Override
	public ApiListResponse listofFilteredEmployee(String filter) {
		try {
			List<Employee> empList = employeeRepository.findFilteredEmployee(filter);
			return new ApiListResponse("success", null, null, empList);
		}catch(Exception e) {
			String errorCode = "ADD_EMPLOYEE_ERROR";
			String errorMessage = e.getMessage();
			List<Employee> empList = new ArrayList<>();
			return new ApiListResponse("error", errorCode, errorMessage, empList);
		}
	}
	
	@Override
	public ApiEmployeeResponse findEmployeeDetail(Long id) {
		try {
			EmployeeDetails empDetail = employeeRepository.findEmployeeDetail(id);
			return new ApiEmployeeResponse("success", null, null, empDetail);
		}catch(Exception e) {
			String errorCode = "ADD_EMPLOYEE_ERROR";
			String errorMessage = e.getMessage();
			EmployeeDetails empDetail = new EmployeeDetails();
			return new ApiEmployeeResponse("error", errorCode, errorMessage, empDetail);
		}
	}
	
	@Override
	public boolean updateEmployee(UpdateEmployee employee, Long id) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		Employee emp;
		if(optionalEmployee.isPresent()) {
			emp = optionalEmployee.get();
			EmployeeShadow employeeShadow = new EmployeeShadow(emp);
			employeeShadowRepository.save(employeeShadow);
			if(employee.getFname() != null) {
				emp.setFname(employee.getFname());
			}
			if(employee.getFullname() != null) {
				emp.setFullname(employee.getFullname());
			}
			if(employee.getDob() != null) {
				emp.setDob(employee.getDob());
			}
			if(employee.getDoj() != null) {
				emp.setDoj(employee.getDoj());
			}
			if(employee.getReportsTo() != null) {
				emp.setReportsTo(employee.getReportsTo());
			}
			if(employee.getSalary() != null) {
				emp.setSalary(employee.getSalary());
			}
			if(employee.getRankId() != null) {
				emp.setRankId(employee.getRankId());
			}
			if(employee.getDeptId() != null) {
				emp.setDeptId(employee.getDeptId());
			}
			employeeRepository.save(emp);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean deleteEmployee(DeleteEmployeeRequest employee, Long id) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		Employee emp;
		if(optionalEmployee.isPresent()) {
			emp = optionalEmployee.get();
			EmployeeShadow employeeShadow = new EmployeeShadow(emp);
			employeeShadowRepository.save(employeeShadow);
			
			employeeRepository.delete(emp);
			return true;
		}
		return false;
	}
	
}
