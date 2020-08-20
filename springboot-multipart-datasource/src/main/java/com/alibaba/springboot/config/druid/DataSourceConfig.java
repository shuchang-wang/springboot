package com.alibaba.springboot.config.druid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


/**
 * @description: 数据源配置
 */
@Configuration
@PropertySource("classpath:datasource.properties")
public class DataSourceConfig {
    private final static Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    // 主库
    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    public DataSource dataSourceM() {
        logger.info("MASTER主库");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    //SLAVE01库
    @Bean(name = "slave01DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave01")
    public DataSource dataSourceSlave01() {
        logger.info("SLAVE01库");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    //SLAVE02库
    @Bean(name = "slave02DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave02")
    public DataSource dataSourceSlave02() {
        logger.info("SLAVE02库");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    // 优先使用，多数据源
    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        DataSource masterDataSource = dataSourceM();
        DataSource slave01DataSource = dataSourceSlave01();
        DataSource slave02DataSource = dataSourceSlave02();

        //设置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
        //配置多数据源
        Map<Object, Object> map = new HashMap<>();
        //key需要跟ThreadLocal中的值对应
        map.put(DataSourceType.MASTER.getName(), masterDataSource);
        map.put(DataSourceType.SLAVE01.getName(), slave01DataSource);
        map.put(DataSourceType.SLAVE02.getName(), slave02DataSource);

        dynamicDataSource.setTargetDataSources(map);
        return dynamicDataSource;
    }

}
