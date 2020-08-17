package com.alibaba.springboot.Controller;

import com.alibaba.springboot.domain.Department;
import com.alibaba.springboot.service.IDeparmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeparmentController {

    @Autowired
    private IDeparmentService deparmentService;

    @GetMapping(name = "获取所有的部门信息", value = "/dept")
    public List<Department> getDepartmentAll() {
        return deparmentService.getDepartmentAll();
    }

    @GetMapping(name = "根据部门ID获取部门信息", value = "/dept/{id}")
    public Department getDepartmentById(@PathVariable("id") Integer id) {
        return deparmentService.getDepartmentById(id);
    }

    @GetMapping(name = "新增部门信息", value = "/dept/add")
    public Department insertDepartment(Department department) {
        deparmentService.insertDepartment(department);
        return department;
    }

    @GetMapping(name = "更新部门信息", value = "/dept/update")
    public Department updateDepartment(Department department) {
        return deparmentService.updateDepartment(department);
    }

    @GetMapping(name = "根据部门ID删除指定部门信息", value = "/dept/delete/{id}")
    public int deleteDepartMentById(@PathVariable("id") Integer id) {
        return deparmentService.deleteDepartMentById(id);
    }
}
