package com.alibaba.springboot.service;


import com.alibaba.springboot.domain.Department;

import java.util.List;

public interface IDeparmentService {

    List<Department> getDepartmentAll();

    Department getDepartmentById(Integer id);

    int deleteDepartMentById(Integer id);

    int insertDepartment(Department department);

    int updateDepartment(Department department);
}
