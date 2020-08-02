package com.alibaba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//@ResponseBody

/**
 * @RestController等价于上面两个的复合体
 @Controller
 @ResponseBody
 public @interface RestController {}
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello !";
    }
}
