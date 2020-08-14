package com.alibaba.springboot.service.impl;

import com.alibaba.springboot.domain.Employee;
import com.alibaba.springboot.mapper.EmployeeMapper;
import com.alibaba.springboot.service.IEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    private static Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getEmployeeAll() {
        return employeeMapper.getEmployeeAll();
    }

    /**
     * 将方法的运行结果进行缓存；以后再要相同的数据，直接从缓存中获取，不用调用方法；
     * @param id
     * @return
     */
    @Override
//    @Cacheable
    public Employee getEmployeeById(Integer id) {
        logger.info("查询第{}号员工", id);
        return employeeMapper.getEmployeeById(id);
    }

    @Override
    public int deleteEmployeeById(Integer id) {
        return employeeMapper.deleteEmployeeById(id);
    }

    @Override
    public int insertEmployee(Employee employee) {
        return employeeMapper.insertEmployee(employee);
    }

    @Override
    public int updateEmployee(Employee employee) {
        return employeeMapper.updateEmployee(employee);
    }
}
