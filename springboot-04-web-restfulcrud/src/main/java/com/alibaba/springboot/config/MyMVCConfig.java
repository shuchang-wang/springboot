package com.alibaba.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//使用WebMVCConfigureAdapter可以来扩展SpringMVC的功能
//@EnableWebMvc //不要接管SpringMVC
@Configuration
public class MyMVCConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //super.addViewControllers(registry);
        //发送 /atalibaba 请求发送到success.html页面
        registry.addViewController("/atalibaba").setViewName("success");
    }

    //所有的WebMvcConfigurerAdapter组件都会使用
    @Bean   //将组件注册到容器中
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/login").setViewName("login");
            }
        };
        return adapter;
    }

}
