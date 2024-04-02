package com.multiplerabbitmq.multiplequeue.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multiplerabbitmq.multiplequeue.entity.Employee;
import com.multiplerabbitmq.multiplequeue.entity.User;

@Service
public class MessageProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProducer.class);

    public void sendMessageToQueue1(Employee employee) {
        LOGGER.info(String.format("Employee produced ->%s", employee));
        rabbitTemplate.convertAndSend("queue1", employee);
    }

    public void sendMessageToQueue2(User user) {
        LOGGER.info(String.format("Employee produced ->%s", user));
        rabbitTemplate.convertAndSend("queue2", user);
    }

}
