package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.EmployeeDetails;
import com.example.demo.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	@Query("Select e from Employee e where lower(e.fullname) like lower(concat('%', :filter, '%'))")
	List<Employee> findFilteredEmployee(@Param("filter") String filter);
	
	@Query("SELECT new com.example.demo.dto.EmployeeDetails(e.fullname, r.rankDesc, d.deptName) " +
		    "FROM Employee e " +
		    "JOIN Rank r ON e.rankId = r.id " +
		    "JOIN Department d ON e.deptId = d.id " +
		    "WHERE e.empId = :id")
	EmployeeDetails findEmployeeDetail(@Param("id") Long id);
}
