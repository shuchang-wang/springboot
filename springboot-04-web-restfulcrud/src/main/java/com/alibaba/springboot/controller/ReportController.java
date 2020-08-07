package com.alibaba.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportController {

    private static Logger logger = LoggerFactory.getLogger(ReportController.class);

    @GetMapping(name = "报表列表", value = "/reports")
    public String list() {
        return "report/reports";
    }

}
