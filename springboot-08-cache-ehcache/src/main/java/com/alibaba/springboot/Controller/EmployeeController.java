package com.alibaba.springboot.Controller;

import com.alibaba.springboot.domain.Employee;
import com.alibaba.springboot.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(name = "更新员工信息", value = "/emp/update")
    public Employee updateDepartment(Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @GetMapping(name = "根据部员工D删除指定员工信息", value = "/emp/delete/{id}")
    public int deleteDepartMentById(@PathVariable("id") Integer id) {
        return employeeService.deleteEmployeeById(id);
    }

    @GetMapping(name = "新增员工信息", value = "/emp/add")
    public Employee insertDepartment(Employee employee) {
        return employeeService.insertEmployee(employee);
    }

    @GetMapping("/emp/lastname/{lastName}")
    public Employee getEmployeeByLastName(@PathVariable("lastName") String lastName){
        return employeeService.getEmployeeByLastName(lastName);
    }
}
