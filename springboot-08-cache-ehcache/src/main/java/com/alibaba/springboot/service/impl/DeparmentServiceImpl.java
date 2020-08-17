package com.alibaba.springboot.service.impl;

import com.alibaba.springboot.domain.Department;
import com.alibaba.springboot.mapper.DepartmentMapper;
import com.alibaba.springboot.service.IDeparmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "Department")
public class DeparmentServiceImpl implements IDeparmentService {

    private static Logger logger = LoggerFactory.getLogger(DeparmentServiceImpl.class);

    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    @Cacheable
    public List<Department> getDepartmentAll() {
        return departmentMapper.getDepartmentAll();
    }

    @Override
    @Cacheable(/*value = "cache",*/key = "#id")
    public Department getDepartmentById(Integer id) {
        logger.info("查询第{}号部门", id);
        Department department = departmentMapper.getDepartmentById(id);
        //获取缓存
        return department;
    }

    @Override
    @CacheEvict(/*value = "cache,"*/beforeInvocation = true)
    public int deleteDepartMentById(Integer id) {
        logger.info("删除第{}号部门", id);
        return departmentMapper.deleteDepartMentById(id);
    }

    @Override
    @CachePut(key = "#result.id")
    public Department updateDepartment(Department department) {
        logger.info("更新第{}号部门", department.getId());
        departmentMapper.updateDepartment(department);
        return department;
    }

    @Override
    @CachePut(key = "#result.id")
    public int insertDepartment(Department department) {
        logger.info("插入部门");
        return departmentMapper.insertDepartment(department);
    }

}
