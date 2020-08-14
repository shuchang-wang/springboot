package com.alibaba.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan(basePackages = "com.alibaba.springboot.mapper")
@SpringBootApplication
public class Springboot08CacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot08CacheApplication.class, args);
	}

}
