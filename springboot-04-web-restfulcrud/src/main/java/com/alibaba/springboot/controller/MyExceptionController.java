package com.alibaba.springboot.controller;

import com.alibaba.springboot.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@ControllerAdvice
public class MyExceptionController {

    //浏览器和客户端统一返回json数据
//    @ResponseBody
//    @ExceptionHandler(UserNotExistException.class)
//    public HashMap exception(Exception e) {
//        HashMap<String, Object> map = new HashMap();
//        map.put("code", "用户不存在");
//        map.put("message", e.getMessage());
//        return map;
//    }

    @ExceptionHandler(UserNotExistException.class)
    public String exception(Exception e, HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap();
        //传入我们自己的错误状态码  4xx 5xx，否则就不会进入定制错误页面的解析流程
        //Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code", "用户不存在");
        map.put("message", "用户出错了哦");
        request.setAttribute("ext",map);
        //请求转发到 /error
        return "forward:/error";
    }

}
