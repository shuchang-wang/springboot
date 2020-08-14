package com.alibaba.springboot.service.impl;

import com.alibaba.springboot.domain.Department;
import com.alibaba.springboot.mapper.DepartmentMapper;
import com.alibaba.springboot.service.IDeparmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeparmentServiceImpl implements IDeparmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public List<Department> getDepartmentAll() {
        return departmentMapper.getDepartmentAll();
    }

    @Override
    public Department getDepartmentById(Integer id) {
        return departmentMapper.getDepartmentById(id);
    }

    @Override
    public int deleteDepartMentById(Integer id) {
        return departmentMapper.deleteDepartMentById(id);
    }

    @Override
    public int insertDepartment(Department department) {
        return departmentMapper.insertDepartment(department);
    }

    @Override
    public int updateDepartment(Department department) {
        return departmentMapper.updateDepartment(department);
    }
}
