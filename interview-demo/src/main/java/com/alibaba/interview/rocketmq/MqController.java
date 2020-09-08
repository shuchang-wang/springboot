package com.alibaba.interview.rocketmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: WSC
 * @Create 2020/9/3 17:54
 */
@RestController
@RequestMapping(value = "/mq")
public class MqController {

    @Autowired
    private ProducerServiceImpl producer;

    @RequestMapping(value = "/push", method = RequestMethod.GET)
    public String test() {
        producer.send("TopicTest1", "push", "测试消息");
        return "Hello Springbootxxxx1111222333444!!!";
    }

}