package com.alibaba.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @param username
     * @param password
     * @return 各种请求
     * @RequestMapping(name = "", value = "",method = RequestMethod.GET/POST/DELETE/PUT/HEADER/PATCH)
     * @GetMapping(name = "", value = "")
     * @PostMapping(name = "", value = "")
     * @DeleteMapping(name = "", value = "")
     * @PutMapping(name = "", value = "")
     * @RequestHeader(name = "", value = "")
     * @PatchMapping(name = "", value = "")
     */
    //@RequestMapping(name = "登入", value = "/user/login", method = RequestMethod.POST)
    @PostMapping(name = "登入", value = "/user/login")
    public String login(String username, String password, Map<String, Object> map, HttpSession session) {
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            if ("admin".equals(username) && "123456".equals(password)) {
                session.setAttribute("loginUser", username);
                //登录成功，防止表单重复提交，可以重定向到主页面
                return "redirect:/main.html";
            } else {
                map.put("errorCode", "404");
                map.put("msg", "用户名或密码错误，请重试！");
                return "login";
            }
        }
        return "main.html";
    }

    @GetMapping(name = "登出", value = "/user/logout/{username}")
    public String logout(@PathVariable String username, HttpSession session) {
        logger.info("登出用户：{}", username);
        Object attribute = session.getAttribute("loginUser");
        logger.info("session值为：{}", attribute);
        if (!StringUtils.isEmpty(attribute)&&attribute.equals(username)) {
            session.removeAttribute("loginUser");
        }
        return "login";
    }
}
