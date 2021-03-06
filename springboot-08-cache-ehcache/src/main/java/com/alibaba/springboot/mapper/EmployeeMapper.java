package com.alibaba.springboot.mapper;

import com.alibaba.springboot.domain.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Mapper注解：用来指定这是一个操作数据库的mapper 如果不想每个接口上写@mapper注解，可以使用@MapperScan(basePackages = "com.alibaba.springboot.mapper")注解添加在应用主启动类上或者添加在Mybatis的自定义配置类上
 */
//@Mapper
//@Mapper或者@MapperScan将接口扫描装配到容器中
public interface EmployeeMapper {

    @Select("select * from employee")
    List<Employee> getEmployeeAll();

    @Select("select * from employee where id =#{id}")
    Employee getEmployeeById(Integer id);

    @Select("select * from employee where last_name =#{lastName}")
    Employee getEmployeeByLastName(String lastName);


    @Insert("insert into employee(lastName,email,gender,d_id) values(#{lastName},#{email},#{gender},#{dId})")
    int insertEmployee(Employee employee);

    @Update("update employee set lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} where id=#{id}")
    void updateEmployee(Employee employee);

    @Delete("delete from employee where id =#{id}")
    int deleteEmployeeById(Integer id);

}
