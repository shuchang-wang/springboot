package com.alibaba.springboot;

import com.alibaba.fastjson.JSON;
import com.alibaba.springboot.domain.Employee;
import com.alibaba.springboot.mapper.EmployeeMapper;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot08CacheApplicationTests {

    private static Logger logger = LoggerFactory.getLogger(Springboot08CacheApplicationTests.class);

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;//操作k-v都是字符串的

    @Autowired
    RedisTemplate redisTemplate;//k-v都是对象的

    @Autowired
    RedisTemplate myRedisTemplate;//k-v都是对象的

    /**
     * 常见的五大数据类型：
     * String(字符串)、List(列表)、Set(集合)、Hash(散列哈希)、ZSet(有序集合)
     * stringRedisTemplate.opsForValue();[String(字符串)]
     * stringRedisTemplate.opsForList();[List(列表)]
     * stringRedisTemplate.opsForSet();[Set(集合)]
     * stringRedisTemplate.opsForHash();[Hash(散列哈希)]
     * stringRedisTemplate.opsForZSet();[ZSet(有序集合)]
     */
    @Test
    public void testRedis01() {
        //给Redis中保存数据
//        Integer append = stringRedisTemplate.opsForValue().append("msg", "hello");
//        logger.info("是否追加成功...{}", append==0?false:true);
        //从Redis中获取指定key的value
        String msg = stringRedisTemplate.opsForValue().get("msg");
        logger.info("String...{}", msg);

//        stringRedisTemplate.opsForList().leftPush("mylist","1");
//        stringRedisTemplate.opsForList().leftPush("mylist","2");
//        stringRedisTemplate.opsForList().leftPush("mylist","3");
        List<String> mylist = stringRedisTemplate.opsForList().range("mylist", 0, -1);
        logger.info("mylist...{}", mylist.toString());

    }

    //测试保存对象
    @Test
    public void testRedis02() {
        Employee employeeById = employeeMapper.getEmployeeById(1);
        //默认如果保存对象使用jdk序列化机制【this.defaultSerializer = new JdkSerializationRedisSerializer】，序列化后的数据保存到redis中
        redisTemplate.opsForValue().set("emp-01",employeeById);
        //1、将数据以json的方式保存
            //(1)将自己对象转为json
            //(2)redisTemplate默认的序列化规则;改变默认的序列化规则
        myRedisTemplate.opsForValue().set("emp-02",employeeById);
    }

    @Test
    public void contextLoads() {
        Employee employeeById = employeeMapper.getEmployeeById(1);
        System.out.println(employeeById);
    }

}
