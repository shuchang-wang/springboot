package com.alibaba.springboot.service;

import com.alibaba.springboot.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @RabbitListener(queues = "alibaba.news")
    public void receive(Book book) {
        System.out.println("接收到消息：" + book);
    }

    @RabbitListener(queues = "alibaba")
    public void receive02(Message message) {
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
