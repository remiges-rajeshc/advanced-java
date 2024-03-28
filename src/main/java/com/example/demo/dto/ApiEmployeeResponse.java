package com.example.demo.dto;

import java.util.List;

import com.example.demo.entity.Employee;

public class ApiEmployeeResponse {
	private String status;
	private String status_code;
	private String errorMessage;
	private EmployeeDetails empDetails;
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
	public EmployeeDetails getEmpDetails() {
		return empDetails;
	}
	public void setEmpDetails(EmployeeDetails empDetails) {
		this.empDetails = empDetails;
	}
	public ApiEmployeeResponse(String status, String status_code, String errorMessage, EmployeeDetails empDetails) {
		this.status = status;
		this.status_code = status_code;
		this.errorMessage = errorMessage;
		this.empDetails = empDetails;
	}
}
