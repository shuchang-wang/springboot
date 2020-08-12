package com.alibaba.springboot.mapper;

import com.alibaba.springboot.bean.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Select("select * from employee")
    List<Employee> getEmployeeAll();

    @Select("select * from employee where id =#{id}")
    Employee getEmployeeById(Integer id);

    @Insert("insert into employee(lastName,email,gender,d_id) values(#{lastName},#{email},#{gender},#{bId})")
    int insertEmployee(Employee employee);

    @Update("update employee set lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{bId} where id=#{id}")
    int updateEmployee(Employee employee);

    @Delete("delete from employee where id =#{id}")
    int deleteEmployeeById(Integer id);

}
