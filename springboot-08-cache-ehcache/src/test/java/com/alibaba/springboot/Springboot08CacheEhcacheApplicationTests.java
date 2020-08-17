package com.alibaba.springboot;

import com.alibaba.springboot.domain.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot08CacheEhcacheApplicationTests {

    private static Logger logger = LoggerFactory.getLogger(Springboot08CacheEhcacheApplicationTests.class);

    @Autowired
    CacheManager cacheManager;

    @Test
    public void test() {
        System.out.println(cacheManager.getClass());//class org.springframework.cache.ehcache.EhCacheCacheManager
        System.out.println(cacheManager.getCacheNames());//[Department, Employee]
        Cache cache = cacheManager.getCache("Department");
        cache.put("3", new Department(66, "公安部"));
        String cacheName = cache.getName();
        logger.info("cacheName:{}", cacheName);//2020-08-17 20:18:44.405  INFO 19088 --- [           main] Springboot08CacheEhcacheApplicationTests : cacheName:Department
        System.out.println(cache.get("3").get());//Department{id=66, departmentName='公安部'}
    }

}
