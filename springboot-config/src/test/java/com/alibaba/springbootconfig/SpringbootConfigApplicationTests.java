package com.alibaba.springbootconfig;

import com.alibaba.springbootconfig.bean.Person;
import com.alibaba.springbootconfig.service.HelloService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SpringBoot单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootConfigApplicationTests {

    @Autowired
    private Person person;

    @Autowired
    private ApplicationContext ioc;

    @Test
    public void testHelloService(){
        boolean helloService = ioc.containsBean("helloService02");
        System.out.println(helloService);
    }


    @Test
    public void contextLoads() {
        System.out.println(person);
    }

}
