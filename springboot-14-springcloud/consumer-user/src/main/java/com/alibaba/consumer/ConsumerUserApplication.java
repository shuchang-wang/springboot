package com.alibaba.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @EnableDiscoveryClient 注解开启服务发现功能
 */
@SpringBootApplication
@EnableDiscoveryClient    //开启服务发现功能
public class ConsumerUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerUserApplication.class, args);
    }
    @Bean   //将RestTemplate加入容器
    @LoadBalanced   //使用负载均衡机制
    public RestTemplate restTemplate() {
        //使用RestTemplate发送http请求
        return new RestTemplate();
    }
}