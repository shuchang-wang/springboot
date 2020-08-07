package com.alibaba.springboot.controller;

import com.alibaba.springboot.dao.DepartmentDao;
import com.alibaba.springboot.dao.EmployeeDao;
import com.alibaba.springboot.domain.Department;
import com.alibaba.springboot.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@Controller
public class EmployeeController {

    private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    /**
     * 查询所有员工返回列表页面
     *
     * @return
     */
    @GetMapping(name = "职员列表", value = "/emps")
    public String list(Map<String, Object> map) {
        //prefix=classpath:/templates/
        //uffix=.html
        Collection<Employee> employees = employeeDao.getAll();
        map.put("emps", employees);
        return "emp/list";
    }

    @GetMapping(name = "跳转到添加员工页面", value = "/emp")
    public String toAddPage(Model model) {
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/add";
    }

    /**
     * 添加员工
     *
     * @param employee
     * @return
     */
    //SpringMVC自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和JavaBean入参的对象里面的属性名一样
    @PostMapping(name = "添加成员", value = "/emp")
    public String addEmployee(Employee employee) {
        employeeDao.save(employee);
        //forward:请求转发  表示转发到一个地址
        //redirect:重定向   表示重定向到一个地址 /代表当前项目路径
        //添加成功跳转到员工列表页面
        return "redirect:/emps";
    }

    /**
     * @param id
     * @param model
     * @return
     */
    @GetMapping(name = "编辑员工", value = "/emp/{id}")
    public String toEditPage(@PathVariable("id") String id, Model model) {
        if (StringUtils.isEmpty(id)) {
            return "redirec:/emps";
        }
        Employee employee = employeeDao.get(Integer.parseInt(id));
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        model.addAttribute("employee", employee);
        return "emp/add";
    }

    /**
     * 修改员工：需要提交员工id
     *
     * @param employee
     * @return
     */
    //SpringMVC自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和JavaBean入参的对象里面的属性名一样
    @PutMapping(name = "修改员工", value = "/emp")
    public String updateEmployee(Employee employee) {
        employeeDao.save(employee);
        //forward:请求转发  表示转发到一个地址
        //redirect:重定向   表示重定向到一个地址 /代表当前项目路径
        //添加成功跳转到员工列表页面
        return "redirect:/emps";
    }

    @DeleteMapping(name = "删除员工", value = "/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }

}
