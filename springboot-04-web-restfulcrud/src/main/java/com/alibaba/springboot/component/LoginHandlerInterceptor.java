package com.alibaba.springboot.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(getClass());

    //登录检测
    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        logger.info("拦截器开始执行。。。。。。");
        HttpSession session = request.getSession();
        Object user = session.getAttribute("loginUser");
        if (user == null) {
            logger.info("参数为：{}",user);
            request.setAttribute("msg", "没有权限，请先登录！");
            request.getRequestDispatcher("/index.html").forward(request, response);
            logger.info("拦截器结束执行。。。。。。");
            return false;
        } else {
            logger.info("参数为：{}",user);
            logger.info("拦截器结束执行。。。。。。");
            return true;
        }
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    //目标方法执行之后
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
