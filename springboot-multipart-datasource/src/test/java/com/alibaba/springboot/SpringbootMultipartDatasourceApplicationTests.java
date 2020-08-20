package com.alibaba.springboot;

import com.alibaba.springboot.config.druid.JdbcContextHolder;
import com.alibaba.springboot.constant.DBConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMultipartDatasourceApplicationTests {

    @Test
    public void contextLoads() throws SQLException {
        String dataSource = JdbcContextHolder.getDataSource();
        JdbcContextHolder.setDbTypeByDatasourceName(DBConstant.MASTER);
        System.out.println(JdbcContextHolder.getDataSource());
        JdbcContextHolder.setDbTypeByDatasourceName(DBConstant.SLAVE01);
        System.out.println(JdbcContextHolder.getDataSource());
        JdbcContextHolder.setDbTypeByDatasourceName(DBConstant.SLAVE02);
        System.out.println(JdbcContextHolder.getDataSource());
    }

}
