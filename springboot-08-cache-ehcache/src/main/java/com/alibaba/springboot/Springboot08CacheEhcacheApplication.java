package com.alibaba.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@MapperScan(basePackages = "com.alibaba.springboot.mapper")
@SpringBootApplication
public class Springboot08CacheEhcacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot08CacheEhcacheApplication.class, args);
	}

}
