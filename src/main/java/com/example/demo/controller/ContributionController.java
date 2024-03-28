package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ContributionController {
    private Map<String, Integer> employeeContributionMap;

    public ContributionController() {
        employeeContributionMap = new HashMap<>();
    }

    @PostMapping("/myhr/employee/updateEmployeeContribution")
    public ResponseEntity<Map<String, Object>> updateEmployeeContribution(
            @RequestParam String departmentName,
            @RequestParam String employeeID,
            @RequestParam(required = false, defaultValue = "0") int count) {

        String key = generateKey(departmentName, employeeID);

        int updatedCount = employeeContributionMap.getOrDefault(key, 0) + count;
        employeeContributionMap.put(key, updatedCount);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("key", key);
        responseBody.put("latestCount", updatedCount);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    private String generateKey(String departmentName, String employeeID) {
        return "user." + departmentName + "." + employeeID;
    }
}