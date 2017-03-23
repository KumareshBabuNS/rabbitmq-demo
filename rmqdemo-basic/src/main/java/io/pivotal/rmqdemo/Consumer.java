package io.pivotal.rmqdemo;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by jaguilar on 3/8/17.
 */
@Service
@EnableRabbit
@RabbitListener(queues = "${app.queueName}")
//@RabbitListener(bindings = @QueueBinding(
//        value = @Queue(value = "${app.queueName}", durable = "true"),
//        exchange = @Exchange(value = "${app.exchangeName}", durable = "true", type = ExchangeTypes.DIRECT)))
public class Consumer {

    @RabbitHandler
    public void consume(@Payload String content) {
        System.out.println("<" + new Date() + "> Consuming message: " + content);
    }
}
