package com.alibaba.springboot.service.impl;

import com.alibaba.springboot.bean.Employee;
import com.alibaba.springboot.config.druid.JdbcContextHolder;
import com.alibaba.springboot.constant.DBConstant;
import com.alibaba.springboot.mapper.EmployeeMapper;
import com.alibaba.springboot.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getEmployeeAll() {
        return employeeMapper.getEmployeeAll();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        JdbcContextHolder.setDbTypeByDatasourceName(DBConstant.SLAVE01);
        return employeeMapper.getEmployeeById(id);
    }

    @Override
    public int insertEmployee(Employee employee) {
        return employeeMapper.insertEmployee(employee);
    }

    @Override
    public int updateEmployee(Employee employee) {
        JdbcContextHolder.setDbTypeByDatasourceName(DBConstant.SLAVE02);
        return employeeMapper.updateEmployee(employee);
    }

    @Override
    public int deleteEmployeeById(Integer id) {
        return employeeMapper.deleteEmployeeById(id);
    }
}
