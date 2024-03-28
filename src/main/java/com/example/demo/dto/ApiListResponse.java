package com.example.demo.dto;

import java.util.List;

import com.example.demo.entity.Employee;

public class ApiListResponse {
	private String status;
	private String status_code;
	private String errorMessage;
	private List<Employee> employeeList;
	public ApiListResponse() {
		
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus_code() {
		return status_code;
	}
	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public List<Employee> getEmployeeList() {
		return employeeList;
	}
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	public ApiListResponse(String status, String status_code, String errorMessage, List<Employee> employeeList) {
		super();
		this.status = status;
		this.status_code = status_code;
		this.errorMessage = errorMessage;
		this.employeeList = employeeList;
	}
}
