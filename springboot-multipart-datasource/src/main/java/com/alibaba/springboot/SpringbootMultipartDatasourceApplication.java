package com.alibaba.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan(basePackages = "com.alibaba.springboot.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootMultipartDatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMultipartDatasourceApplication.class, args);
	}

}
