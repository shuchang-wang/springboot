package com.alibaba.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 如果不想每个接口上写@mapper注解，可以使用@MapperScan(basePackages = "com.alibaba.springboot.mapper")注解添加在应用主启动类上或者添加在Mybatis的自定义配置类上
 */
@MapperScan(basePackages = "com.alibaba.springboot.mapper")
@SpringBootApplication
public class Springboot05DataMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot05DataMybatisApplication.class, args);
	}

}
