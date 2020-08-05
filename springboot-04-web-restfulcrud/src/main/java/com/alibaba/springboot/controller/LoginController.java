package com.alibaba.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    /**
     * @param username
     * @param password
     * @return
     *
     * 各种请求
     * @RequestMapping(name = "", value = "",method = RequestMethod.GET/POST/DELETE/PUT/HEADER/PATCH)
     * @GetMapping(name = "", value = "")
     * @PostMapping(name = "", value = "")
     * @DeleteMapping(name = "", value = "")
     * @PutMapping(name = "", value = "")
     * @RequestHeader(name = "", value = "")
     * @PatchMapping(name = "", value = "")
     */
    //@RequestMapping(name = "登录", value = "/user/login", method = RequestMethod.POST)
    @PostMapping(name = "登录", value = "/user/login")
    public String login(String username, String password, Map<String,Object> map, HttpSession session) {
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            if ("admin".equals(username) && "123456".equals(password)) {
                session.setAttribute("loginUser",username);
                //登录成功，防止表单重复提交，可以重定向到主页面
                return "redirect:/main.html";
            } else {
                map.put("errorCode","404");
                map.put("msg","用户名或密码错误，请重试！");
                return "login";
            }
        }
        return "main.html";
    }
}
