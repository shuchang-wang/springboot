package com.alibaba.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {
    private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    /**
     * public enum RequestMethod {
     * GET,
     * HEAD,
     * POST,
     * PUT,
     * PATCH,
     * DELETE,
     * OPTIONS,
     * TRACE;
     * }
     *
     * @return
     */
    //method = RequestMethod.GET/POST/PUT/DELETE/HEAD/OPTIONS/PATCH/TRACE
    @RequestMapping(name = "hello", value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String sayHello() {
        return "Hello EveryBody!";
    }

    //查找数据返回页面
    @RequestMapping(name = "success", value = "/success", method = RequestMethod.GET)
    public String success(Map<String, Object> map) {
        map.put("hello", "<h1>你好</h1>");
        map.put("users", Arrays.asList("张三", "李四", "王五"));
        //classpath:/templates/success.html
        return "success";
    }

//    @RequestMapping(name = "首页",value = {"/","index.html"},method = RequestMethod.GET)
//    public String index(){
//        return "index";
//    }

}
