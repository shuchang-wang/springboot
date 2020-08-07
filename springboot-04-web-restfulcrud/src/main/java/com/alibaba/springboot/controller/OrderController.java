package com.alibaba.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @GetMapping(name = "订单列表", value = "/orders")
    public String list() {
        return "order/orders";
    }

}
