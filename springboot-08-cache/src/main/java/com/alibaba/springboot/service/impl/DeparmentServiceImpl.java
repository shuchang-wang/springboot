package com.alibaba.springboot.service.impl;

import com.alibaba.springboot.domain.Department;
import com.alibaba.springboot.mapper.DepartmentMapper;
import com.alibaba.springboot.service.IDeparmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheManager = "deptpCacheManager")
public class DeparmentServiceImpl implements IDeparmentService {

    private static Logger logger = LoggerFactory.getLogger(DeparmentServiceImpl.class);

    @Autowired
    DepartmentMapper departmentMapper;

    @Qualifier("deptpCacheManager")
    @Autowired
    RedisCacheManager deptpCacheManager;

    @Override
    public List<Department> getDepartmentAll() {
        return departmentMapper.getDepartmentAll();
    }

    /**
     * 注解的方式：
     *
     * 缓存的数据能存入redis;
     * 第二次从缓存中查询就不能反序列化回来；
     * 存的是dept的json数据，CacheManager默认使用RedisTem<Object,Employee>操作redis缓存的
     * @param id
     * @return
     */
//    @Override
//    @Cacheable(value = "dept",key = "#id")
//    public Department getDepartmentById(Integer id) {
//        logger.info("查询第{}号部门", id);
//        return departmentMapper.getDepartmentById(id);
//    }
    /**
     * 编码的方式进行对redis缓存进行操作
     */
    @Override
    public Department getDepartmentById(Integer id) {
        logger.info("查询第{}号部门", id);
        Department department = departmentMapper.getDepartmentById(id);
        //获取缓存
        Cache dept = deptpCacheManager.getCache("dept");
        dept.put(id,department);
        return department;
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
