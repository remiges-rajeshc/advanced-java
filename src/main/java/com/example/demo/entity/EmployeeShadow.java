package com.example.demo.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_shadow")
public class EmployeeShadow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
    @Column(name = "empid", nullable = false)
    private Long empId;

    @Column(name = "fname", nullable = false)
    private String fname;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "dob", nullable = false)
    private Date dob;

    @Column(name = "doj", nullable = false)
    private Date doj;

    @Column(name = "salary", nullable = false)
    private Integer salary;

    @Column(name = "reportsto")
    private Long reportsTo;

    @Column(name = "deptid")
    private Long deptId;

    @Column(name = "rankid")
    private Long rankId;

    @Column(name = "createdat")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updatedat")
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name = "client_reqid", nullable = false)
    private String clientReqId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

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

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Long getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(Long reportsTo) {
		this.reportsTo = reportsTo;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getRankId() {
		return rankId;
	}

	public void setRankId(Long rankId) {
		this.rankId = rankId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getClientReqId() {
		return clientReqId;
	}

	public void setClientReqId(String clientReqId) {
		this.clientReqId = clientReqId;
	}
	
	public EmployeeShadow(Employee emp) {
		this.fname = emp.getFname();
		this.fullname = emp.getFullname();
		this.empId = emp.getEmpId();
		this.dob = emp.getDob();
		this.doj = emp.getDoj();
		this.clientReqId = emp.getClientReqId();
		this.createdAt = emp.getCreatedAt();
		this.updatedAt = emp.getUpdatedAt();
		this.salary = emp.getSalary();
		this.rankId = emp.getRankId();
		this.deptId = emp.getDeptId();
		this.reportsTo = emp.getReportsTo();
	}
}
