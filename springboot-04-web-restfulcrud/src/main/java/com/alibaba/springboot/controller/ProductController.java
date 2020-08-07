package com.alibaba.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping(name = "产品列表", value = "/products")
    public String list() {
        return "product/products";
    }

}
