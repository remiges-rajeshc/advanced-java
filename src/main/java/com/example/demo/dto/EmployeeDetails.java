package com.example.demo.dto;

public class EmployeeDetails {
	private String employeeName;
    private String rankDesc;
    private String deptName;
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getRankDescription() {
		return rankDesc;
	}
	public void setRankDescription(String rankDesc) {
		this.rankDesc = rankDesc;
	}
	public String getDepartmentName() {
		return deptName;
	}
	public void setDepartmentName(String deptName) {
		this.deptName = deptName;
	}
	public EmployeeDetails(String employeeName, String rankDesc, String deptName) {
		this.employeeName = employeeName;
		this.rankDesc = rankDesc;
		this.deptName = deptName;
	}
	public EmployeeDetails() {
		
	}
}
