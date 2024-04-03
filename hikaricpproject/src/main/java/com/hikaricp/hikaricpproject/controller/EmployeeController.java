package com.hikaricp.hikaricpproject.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hikaricp.hikaricpproject.entity.Employee;
import com.hikaricp.hikaricpproject.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
  private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

  @Autowired
  EmployeeService employeeService;

  @PostMapping("/user")
  public ResponseEntity<Employee> createUser(@RequestBody Employee employee) {
    Employee savedEmployee = employeeService.createEmployee(employee);
    logger.info("Fetching all employees");
    return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
  }

  @GetMapping("/all")
  public ResponseEntity<List<Employee>> getAllUsers() {
    List<Employee> users = employeeService.getAllUsers();
    logger.info("Creating employee: {}", users);
    return new ResponseEntity<>(users, HttpStatus.OK);
  }
}