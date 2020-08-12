package com.alibaba.springboot.controller;

import com.alibaba.springboot.bean.Department;
import com.alibaba.springboot.service.IDeparmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeparmentController {

    @Autowired
    private IDeparmentService deparmentService;

//    @GetMapping(name = "获取所有的部门信息", value = "/dept")
//    public List<Department> getDepartmentAll() {
//        return deparmentService.getDepartmentAll();
//    }

    @GetMapping(name = "根据部门ID获取部门信息", value = "/dept/{id}")
    public Department getDepartmentById(@PathVariable("id") Integer id) {
        return deparmentService.getDepartmentById(id);
    }

    @GetMapping(name = "新增部门信息", value = "/dept")
    public Department insertDepartment(Department department) {
         deparmentService.insertDepartment(department);
        return department;
    }

    @PutMapping(name = "更新部门信息", value = "/dept")
    public int updateDepartment(Department department) {
        return deparmentService.updateDepartment(department);
    }

    @DeleteMapping(name = "根据部门ID删除指定部门信息", value = "/dept/{id}")
    public int deleteDepartMentById(@PathVariable("id") Integer id) {
        return deparmentService.deleteDepartMentById(id);
    }
}
