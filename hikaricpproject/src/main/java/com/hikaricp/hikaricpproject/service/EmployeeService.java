package com.hikaricp.hikaricpproject.service;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hikaricp.hikaricpproject.entity.Employee;
import com.hikaricp.hikaricpproject.repository.EmployeeRepository;
import com.zaxxer.hikari.HikariConfigMXBean;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;

@Service
public class EmployeeService {

    @Autowired
    private DataSource dataSource;
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {

        HikariConfigMXBean hikariConfig = (HikariConfigMXBean) dataSource;
        
        System.out.println("HikariCP Properties:");
        System.out.println("Maximum Pool Size: " + hikariConfig.getMaximumPoolSize());
        System.out.println("Minimum Idle: " + hikariConfig.getMinimumIdle());
        System.out.println("Connection Timeout: " + hikariConfig.getConnectionTimeout() + "ms");
        System.out.println("Idle Timeout: " + hikariConfig.getIdleTimeout() + "ms");
        System.out.println("Max Lifetime: " + hikariConfig.getMaxLifetime() + "ms");
        System.out.println("Pool Name: " + hikariConfig.getPoolName());

        
        Employee emp= employeeRepository.save(employee);
        int activeConnections = ((HikariDataSource) dataSource).getHikariPoolMXBean().getActiveConnections();
        System.out.println("Active Connections: " + activeConnections);
        return emp;

    }

    public List<Employee> getAllUsers() {
        return employeeRepository.findAll();
    }
}
