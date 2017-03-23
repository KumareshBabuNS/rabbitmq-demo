package io.pivotal.rmqdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jaguilar on 3/10/17.
 */
@RestController
public class MainController {

    @Autowired
    Producer producer;

    @RequestMapping("/")
    public String sendMessage(@RequestParam("message") String message) {
        producer.sendMessage(message);
        return "Message Sent";
    }
}
