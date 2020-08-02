package com.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication来标注一个主程序类，说明是一个springboot应用
@SpringBootApplication
public class HelloWorldMainApplication {
    public static void main(String[] args) {
        //启动springboot应用
        SpringApplication.run(HelloWorldMainApplication.class, args);
    }
}
