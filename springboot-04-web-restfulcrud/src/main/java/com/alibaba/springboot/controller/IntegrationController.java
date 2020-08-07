package com.alibaba.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IntegrationController {

    private static Logger logger = LoggerFactory.getLogger(IntegrationController.class);

    @GetMapping(name = "集成列表", value = "/integrations")
    public String list() {
        return "integration/integrations";
    }

}
