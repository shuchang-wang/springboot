package com.alibaba.springbootconfig.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${person.lastName}")
    private String lastName;

    @RequestMapping("/sayHello")
    public String sayHello() {
        return "Hello " + lastName;
    }

}
