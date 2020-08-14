package com.alibaba.springboot.Controller;

import com.alibaba.springboot.domain.Employee;
import com.alibaba.springboot.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping(name = "获取所有的员工信息", value = "/emp")
    public List<Employee> getDepartmentAll() {
        return employeeService.getEmployeeAll();
    }

    @GetMapping(name = "根据员工ID获取员工信息", value = "/emp/{id}")
    public Employee getDepartmentById(@PathVariable("id") Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping(name = "新增员工信息", value = "/emp")
    public int insertDepartment(Employee employee) {
        return employeeService.insertEmployee(employee);
    }

    @PutMapping(name = "更新员工信息", value = "/emp")
    public int updateDepartment(Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping(name = "根据部员工D删除指定员工信息", value = "/emp/{id}")
    public int deleteDepartMentById(@PathVariable("id") Integer id) {
        return employeeService.deleteEmployeeById(id);
    }
}
