package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.RedisService;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;
  //question no-20
    @PostMapping("/add-key")
    public ResponseEntity<String> addKey(@RequestParam String empName, @RequestParam(defaultValue = "0") int value) {
        redisService.addKey("user." + empName, value);
        return ResponseEntity.ok("Key added successfully");
    }

    @GetMapping("/get-value")
    public ResponseEntity<Integer> getValue(@RequestParam String empName) {
        Integer value = redisService.getValue("user." + empName);
        return ResponseEntity.ok(value);
    }

    @PostMapping("/increment")
    public ResponseEntity<String> increment(@RequestParam String empName) {
        redisService.increment("user." + empName);
        return ResponseEntity.ok("Value incremented successfully");
    }

    @PostMapping("/decrement")
    public ResponseEntity<String> decrement(@RequestParam String empName) {
        redisService.decrement("user." + empName);
        return ResponseEntity.ok("Value decremented successfully");
    }

    @PostMapping("/set-ttl")
    public ResponseEntity<String> setTTL(@RequestParam String empName, @RequestParam long timeout, @RequestParam TimeUnit unit) {
        redisService.setTTL("user." + empName, timeout, unit);
        return ResponseEntity.ok("TTL set successfully");
    }

     @GetMapping("/{department}/{employeeId}/contribution")
    public ResponseEntity<Object> getEmployeeContribution(@PathVariable String department,
            @PathVariable String employeeId) {

     
            int contribution = redisService.getEmployeeContribution(department, employeeId);
            return ResponseEntity.ok(contribution);
           
       
    }
}
