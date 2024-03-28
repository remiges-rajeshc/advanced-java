package com.example.demo.dto;

import com.example.demo.entity.Employee;

public class ApiResponse {
	private String status;
	private String status_code;
	private String errorMessage;
	private Employee employee;
	public ApiResponse(String status, String status_code, String errorMessage, Employee employee) {
		this.status = status;
		this.status_code = status_code;
		this.errorMessage = errorMessage;
		this.employee = employee;
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
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
