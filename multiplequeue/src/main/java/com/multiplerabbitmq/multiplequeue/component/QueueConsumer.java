package com.multiplerabbitmq.multiplequeue.component;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.multiplerabbitmq.multiplequeue.entity.Employee;
import com.multiplerabbitmq.multiplequeue.entity.User;

@Component
public class QueueConsumer {
    @RabbitListener(queues = "queue1")
    public void receiveMessageFromQueue1(Employee message) {
        System.out.println("Received message from queue1: " + message.toString());
    
    }

    @RabbitListener(queues = "queue2")
    public void receiveMessageFromQueue2(User message) {
        System.out.println("Received message from queue2: " + message.toString());
        
    }
    @RabbitListener(queues = "queue2")
    public void receiveMessageFromQueue3(User message) {
        System.out.println("Received message from queue6: " + message.toString());
        
    }
}



