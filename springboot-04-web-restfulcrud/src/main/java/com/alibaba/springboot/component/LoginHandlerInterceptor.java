package com.alibaba.springboot.component;

import com.alibaba.springboot.dao.DepartmentDao;
import com.alibaba.springboot.dao.EmployeeDao;
import com.alibaba.springboot.entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EmployeeDao employeeDao;

    //登录检测
    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        logger.info("测试拦截器中能否注入依赖开始。。。。。。。。。。。。。");
        logger.info("注入的employeeDao依赖值是：{}",employeeDao);
        Collection<Employee> employees = employeeDao.getAll();
        logger.info("测试拦截器中能否注入依赖结束。。。。。。。。。。。。。");
        logger.info("拦截器开始执行。。。。。。");
        HttpSession session = request.getSession();
        Object user = session.getAttribute("loginUser");
        if (user == null) {
            logger.info("参数为：{}", user);
            request.setAttribute("msg", "没有权限，请先登录！");
            request.getRequestDispatcher("/index.html").forward(request, response);
            logger.info("拦截器结束执行。。。。。。");
            return false;
        } else {
            logger.info("参数为：{}", user);
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
