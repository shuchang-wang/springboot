package com.alibaba.springboot.service.impl;

import com.alibaba.springboot.domain.Employee;
import com.alibaba.springboot.mapper.EmployeeMapper;
import com.alibaba.springboot.service.IEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "Employee")
public class EmployeeServiceImpl implements IEmployeeService {

    private static Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getEmployeeAll() {
        return employeeMapper.getEmployeeAll();
    }

    @Override
    @Cacheable(key = "#id")
    public Employee getEmployeeById(Integer id) {
        logger.info("查询第{}号员工", id);
        return employeeMapper.getEmployeeById(id);
    }

    @Override
    @CachePut(key = "#result.id")
    public Employee updateEmployee(Employee employee) {
        logger.info("updateEmployee{}", employee);
        employeeMapper.updateEmployee(employee);
        return employee;
    }

    @Override
    @CacheEvict
    public int deleteEmployeeById(Integer id) {
        logger.info("删除第{}号员工信息", id);
        return employeeMapper.deleteEmployeeById(id);
    }

    /**
     * @param lastName
     * @return
     * @Caching：定义复杂的缓存规则
     */
    @Override
    @Caching(
            cacheable = {
                    @Cacheable(/*value = "emp",*/key = "#lastName")
            },
            put = {
                    @CachePut(/*value = "emp",*/key = "#result.id"),
                    @CachePut(/*value = "emp",*/key = "#result.email"),
            }
    )
    public Employee getEmployeeByLastName(String lastName) {
        return employeeMapper.getEmployeeByLastName(lastName);
    }

    @Override
    public Employee insertEmployee(Employee employee) {
        employeeMapper.insertEmployee(employee);
        return employee;
    }

}
