package com.alibaba.actuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 自定义健康状态指示器
 * 1、编写一个指示器 实现 HealthIndicator接口
 * 2、指示器的名字，命名规则	XxxHealthIndicator
 * 3、加入容器中
 */
@SpringBootApplication
public class Springboot16ActuatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot16ActuatorApplication.class, args);
    }

}