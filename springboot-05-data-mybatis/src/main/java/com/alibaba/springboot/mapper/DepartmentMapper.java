package com.alibaba.springboot.mapper;

import com.alibaba.springboot.bean.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;


//@Mapper注解：用来指定这是一个操作数据库的mapper
@Mapper
public interface DepartmentMapper {

    @Select("select id,departmentName from department")
    List<Department> getDepartmentAll();

    @Select("select id,departmentName from department where id =#{id}")
    Department getDepartmentById(Integer id);


    @Insert("insert into deparment(departmentName) values(#{departmentName})")
    int insertDepartment(Department department);

    @Update("update deparment set departmentName=#{departmentName} where id=#{id}")
    int updateDepartment(Department department);

    @Delete("delete from deparment where id =#{id}")
    int deleteDepartMentById(Integer id);
}
