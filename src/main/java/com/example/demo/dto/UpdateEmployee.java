package com.example.demo.dto;

import java.util.Date;

public class UpdateEmployee {
	String fname;
	String fullname;
	Date dob;
	Date doj;
	Integer salary;
	Long reportsTo;
	Long rankId;
	Long deptId;
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public Long getRankId() {
		return rankId;
	}
	public void setRankId(Long rankId) {
		this.rankId = rankId;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public Long getReportsTo() {
		return reportsTo;
	}
	public void setReportsTo(Long reportsTo) {
		this.reportsTo = reportsTo;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public UpdateEmployee(String fname, String fullname, Date dob, Date doj, Integer salary, Long reportsTo, Long rankId,
			Long deptId) {
		this.fname = fname;
		this.fullname = fullname;
		this.dob = dob;
		this.doj = doj;
		this.salary = salary;
		this.reportsTo = reportsTo;
		this.rankId = rankId;
		this.deptId = deptId;
	}
	public UpdateEmployee() {

	}
	
}
