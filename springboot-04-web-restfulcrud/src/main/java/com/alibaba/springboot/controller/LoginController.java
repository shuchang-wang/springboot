package com.alibaba.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
    public String login(String username, String password) {
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            if ("admin".equals(username) && "123456".equals(password)) {
                return "dashboard";
            } else {
                return "login";
            }
        }
        return "login";
    }
}
