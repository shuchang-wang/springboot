package com.alibaba.springboot.controller;

import com.alibaba.springboot.dao.EmployeeDao;
import com.alibaba.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Map;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;
    /**
     * 查询所有员工返回列表页面
     * @return
     */
    @GetMapping(name = "职员列表", value = "/emps")
    public String list(Map<String,Object> map) {
        //prefix=classpath:/templates/
        //uffix=.html
        Collection<Employee> employees = employeeDao.getAll();
        map.put("emps",employees);
        return "emp/list";
    }

}
