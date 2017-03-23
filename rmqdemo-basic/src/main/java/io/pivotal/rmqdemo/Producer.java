package io.pivotal.rmqdemo;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by jaguilar on 3/10/17.
 */
@Service
public class Producer {

    @Value("${app.queueName}")
    private String queueName;

    @Value("${app.exchangeName}")
    private String exchangeName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    Queue queue() {
        return new Queue(queueName, true);
    }

    @Bean
    Exchange exchange() {
        return new DirectExchange(exchangeName, true, false);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }

    public void sendMessage(String message) {
        System.out.println("<" + new Date() + "> Sending message to exchange: " + exchangeName);
        rabbitTemplate.convertAndSend(exchangeName, queueName, message);
        System.out.println("<" + new Date() + "> Message sent!: " + message);
    }
}
