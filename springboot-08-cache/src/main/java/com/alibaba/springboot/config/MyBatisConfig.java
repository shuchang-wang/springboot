package com.alibaba.springboot.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 如果不想每个接口上写@mapper注解，可以使用@MapperScan(basePackages = "com.alibaba.springboot.mapper")注解添加在应用主启动类上或者添加在Mybatis的自定义配置类上
 */
//@MapperScan(basePackages = "com.alibaba.springboot.mapper")
//@Configuration
public class MyBatisConfig {

    //自定义Mybatis的自动配置规则
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
