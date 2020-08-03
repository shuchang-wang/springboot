package com.alibaba.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello EveryBody!";
    }

    @RequestMapping("/success")
    public String success() {
        //classpath:/templates/success.html
        return "success";
    }

}
