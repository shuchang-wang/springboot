package com.alibaba.springboot.mapper;

import com.alibaba.springboot.domain.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Mapper注解：用来指定这是一个操作数据库的mapper 如果不想每个接口上写@mapper注解，可以使用@MapperScan(basePackages = "com.alibaba.springboot.mapper")注解添加在应用主启动类上或者添加在Mybatis的自定义配置类上
 */
//@Mapper
public interface DepartmentMapper {

    @Select("select * from department")
    List<Department> getDepartmentAll();

    @Select("select * from department where id =#{id}")
    Department getDepartmentById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into department(department_name) values(#{departmentName})")
    int insertDepartment(Department department);

    @Update("update department set department_name=#{departmentName} where id=#{id}")
    int updateDepartment(Department department);

    @Delete("delete from department where id =#{id}")
    int deleteDepartMentById(Integer id);
}
