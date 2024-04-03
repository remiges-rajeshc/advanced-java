package com.hikaricp.hikaricpproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hikaricp.hikaricpproject.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}


