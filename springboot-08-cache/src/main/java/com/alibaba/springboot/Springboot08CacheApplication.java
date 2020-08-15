package com.alibaba.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 一、搭建基本环境
 * 1、导入数据库文件，创建department和employee表
 * 2、创建JavaBean封装数据对象
 * 3、整合MyBatis操作数据库
 * 		1.配置数据源信息
 * 		2.使用注解版的Mybatis；
 * 			1）、@MapperScan指定需要扫描的mapper接口所在的包
 * 二、快速体验缓存
 *      步骤：
 *          1、开启基于注解的缓存
 *              @EnableCaching
 *          2、标注缓存注解即可
 *              @Cacheable
 *              @CacheEvict
 *              @CachePut
 *              @EnableCaching
 *  默认使用的是ConcurrentMapCacheManager==ConcurrentMapCache；将数据保存在ConcurrentMap<Object,Object>
 *  开发中使用缓存中间件：redis、memcached、ehcache等待
 */
@MapperScan(basePackages = "com.alibaba.springboot.mapper")
@SpringBootApplication
@EnableCaching
public class Springboot08CacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot08CacheApplication.class, args);
	}

}
