package com.multiplerabbitmq.multiplequeue.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.multiplerabbitmq.multiplequeue.entity.Employee;
import com.multiplerabbitmq.multiplequeue.entity.User;
import com.multiplerabbitmq.multiplequeue.service.MessageProducer;

@RestController
@RequestMapping("/api")
public class MessageController {
    @Autowired
    private MessageProducer messageProducer;

    @PostMapping("/queue1")
    public ResponseEntity<String> sendMessageToQueue1(@RequestBody Employee employee) {
        try {
            messageProducer.sendMessageToQueue1(employee);
            return ResponseEntity.ok("Message sent to queue1 successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send message to queue1: " + e.getMessage());
        }
    }

    @PostMapping("/queue2")
    public ResponseEntity<String> sendMessageToQueue2(@RequestBody User user) {
        try {
            messageProducer.sendMessageToQueue2(user);
            return ResponseEntity.ok("Message sent to queue2 successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send message to queue2: " + e.getMessage());
        }
    }
}

