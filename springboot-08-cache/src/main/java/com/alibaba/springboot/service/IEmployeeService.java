package com.alibaba.springboot.service;


import com.alibaba.springboot.domain.Employee;

import java.util.List;

public interface IEmployeeService {

    List<Employee> getEmployeeAll();

    Employee getEmployeeById(Integer id);

    int deleteEmployeeById(Integer id);

    int insertEmployee(Employee employee);

    int updateEmployee(Employee employee);
}
