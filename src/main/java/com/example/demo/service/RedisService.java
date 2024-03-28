package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;
@Component
public class RedisService {
    private static RedisService instance;
    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;

    private RedisService() {
    }

    public static RedisService getInstance() {
        if (instance == null) {
            instance = new RedisService();
        }
        return instance;
    }

    public void addKey(String key, int value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Integer getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void increment(String key) {
        redisTemplate.opsForValue().increment(key);
    }

    public void decrement(String key) {
        redisTemplate.opsForValue().decrement(key);
    }

    public void setTTL(String key, long timeout, TimeUnit unit) {
        redisTemplate.expire(key, timeout, unit);
    }

    public int getEmployeeContribution(String department, String employeeId) {
        String key = "user." + department + "." + employeeId;
        Integer contribution = redisTemplate.opsForValue().get(key);
        if (contribution == null) {
            contribution = fetchEmployeeContributionFromDatabase(department, employeeId);
            cacheEmployeeContributionInRedis(department, employeeId, contribution);
        }
        return contribution;
    }
    private int fetchEmployeeContributionFromDatabase(String department, String employeeId) {
        // Perform database operation to fetch contribution
        // For example:
        // Employee employee = employeeRepository.findByDepartmentAndEmpId(department,
        // employeeId);
        // return employee.getContribution();
        // For demonstration, returning a dummy value
        return 7;
    }
    private void cacheEmployeeContributionInRedis(String department, String employeeId, int contribution) {
        String key = "user." + department + "." + employeeId;
        redisTemplate.opsForValue().set(key, contribution);
        // Set TTL for the key
        redisTemplate.expire(key, 3, TimeUnit.MINUTES);
    }

    // Problem 23 Ends Here.

}
