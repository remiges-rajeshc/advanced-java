
package com.multiplerabbitmq.multiplequeue.config;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.BindingBuilder.GenericArgumentsConfigurer;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Exchange exchange() {
        return new TopicExchange("my_exchange");
    }
    @Bean
    public Queue queue1() {
        return new Queue("queue1", false);
    }

    @Bean
    public Queue queue2() {
        return new Queue("queue2", false);
    }

    @Bean
    public GenericArgumentsConfigurer binding1(Queue queue1, Exchange exchange) {
        return BindingBuilder.bind(queue1).to(exchange).with("queue1_routing_key");
    }

    @Bean
    public GenericArgumentsConfigurer binding2(Queue queue2, Exchange exchange) {
        return BindingBuilder.bind(queue2).to(exchange).with("queue2_routing_key");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
    
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
