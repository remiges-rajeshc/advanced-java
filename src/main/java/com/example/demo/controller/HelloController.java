package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoggingInterceptor;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class HelloController {


        private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

//question no-1
    @GetMapping("/hello")
    public ResponseEntity<Map<String, Object>> sayHello() {
        // Constructing the JSON response format
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("message", "Hello World");

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("status_code", "");
        response.put("status_msg", "");
        response.put("data", responseData);
        response.put("_reqid", UUID.randomUUID().toString());
        response.put("_server_ts", Instant.now().toString());

        logger.debug("Received request: {} {}");
        return ResponseEntity.ok(response);
    }

    // Q.no.-2
    @PostMapping("/hello")
    public ResponseEntity<Map<String, Object>> sayHello(@RequestBody Map<String, Object> requestBody) {
        // Extracting name from the request data
        Map<String, Object> data = (Map<String, Object>) requestBody.get("data");
        String name = (String) data.get("name");

        // Constructing the JSON response format
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("message", "Hello " + name);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("status_code", "");
        response.put("status_msg", "");
        response.put("data", responseData);

        // Adding additional request/response attributes
        response.put("_reqid", requestBody.get("generateRanId"));
        response.put("_server_ts", Instant.now().toString());

        return ResponseEntity.ok(response);
    }
 //question-3
    @PostMapping("/mysum")
    public ResponseEntity<Object> performSum(@RequestBody Map<String, Object> requestBody) {
        String operation = (String) requestBody.get("operation");
        Double num1 = (Double) requestBody.get("num1");
        Double num2 = (Double) requestBody.get("num2");

        if (operation == null || num1 == null || num2 == null) {
            return ResponseHandler.generateResponse("Invalid request format or num1 or num2 is null", HttpStatus.BAD_REQUEST, null);
        }

        double result;
        switch (operation) {
            case "add":
                result = num1 + num2;
                break;
            case "subtract":
                result = num1 - num2;
                break;
            case "multiply":
                result = num1 * num2;
                break;
            case "divide":
                if (num2 == 0) {
                    return ResponseHandler.generateResponse("Division by zero", HttpStatus.BAD_REQUEST, null);
                }
                result = num1 / num2;
                break;
            default:
                return ResponseHandler.generateResponse("Invalid operation specified", HttpStatus.BAD_REQUEST, null);
        }

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("result", result);

        return ResponseHandler.generateResponse("Operation performed successfully", HttpStatus.OK, responseData);
    }

 class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", message);
            map.put("status", status.value());
            map.put("data", responseObj);

            return new ResponseEntity<Object>(map,status);
    }
}
//question no-4
@PostMapping("/mycalc")
public ResponseEntity<Object> performCalculation(@RequestBody Map<String, Object> requestBody) {
    List<Double> numbers = (List<Double>) requestBody.get("numbers");
    String operation = (String) requestBody.get("operation");

    if (numbers == null || operation == null) {
        return ResponseHandler.generateResponse("Invalid request format: numbers or operation is null", HttpStatus.BAD_REQUEST, null);
    }

    double result;
    String message;
    switch (operation) {
        case "mean":
            result = calculateMean(numbers);
            message = "Mean value calculated successfully";
            break;
        case "min":
            result = calculateMin(numbers);
            message = "Minimum value calculated successfully";
            break;
        case "max":
            result = calculateMax(numbers);
            message = "Maximum value calculated successfully";
            break;
        default:
            return ResponseHandler.generateResponse("Invalid operation specified", HttpStatus.BAD_REQUEST, null);
    }

    // Construct response data
    StringBuilder responseData = new StringBuilder();
    responseData.append("Operation: ").append(operation).append("\n");
    responseData.append("Result: ").append(result);

    return ResponseHandler.generateResponse(message, HttpStatus.OK, responseData.toString());
}

private double calculateMean(List<Double> numbers) {
    if (numbers.isEmpty()) {
        return 0.0;
    }
    double sum = 0.0;
    for (double num : numbers) {
        sum += num;
    }
    return sum / numbers.size();
}

private double calculateMin(List<Double> numbers) {
    if (numbers.isEmpty()) {
        return 0.0;
    }
    double min = numbers.get(0);
    for (double num : numbers) {
        if (num < min) {
            min = num;
        }
    }
    return min;
}

private double calculateMax(List<Double> numbers) {
    if (numbers.isEmpty()) {
        return 0.0;
    }
    double max = numbers.get(0);
    for (double num : numbers) {
        if (num > max) {
            max = num;
        }
    }
    return max;
}


//question no-5
@Value("${hi.property1:NULL}")
private String property1;

@Value("${hi.property2:NULL}")
private String property2;

// Add more @Value annotations for additional properties

@PostMapping("/myproperties")
public ResponseEntity<Map<String, String>> getProperties(@RequestBody List<String> propertyIdentifiers) {
    Map<String, String> result = new HashMap<>();
    for (String identifier : propertyIdentifiers) {
        switch (identifier) {
            case "property1":
                result.put(identifier, property1);
                break;
            case "property2":
                result.put(identifier, property2);
                break;
            // Add cases for additional properties
            default:
                result.put(identifier, "NULL");
                break;
        }
    }
    return ResponseEntity.ok(result);
}
}



