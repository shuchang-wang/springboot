package com.alibaba.springboot.config.druid;

import com.alibaba.springboot.constant.DBConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description: 数据源上下文JdbcContextHolder
 */
public class JdbcContextHolder {
    private final static ThreadLocal<String> local = new ThreadLocal<>();
    private final static Logger logger = LoggerFactory.getLogger(JdbcContextHolder.class);

    public static void putDataSource(DataSourceType type) {
        logger.info("数据库切换为:{}", type.getName());
        local.set(type.getName());
    }

    public static void putDataSource(String datasourceName) {
        if (DBConstant.MASTER.equals(datasourceName)) {
            putDataSource(DataSourceType.MASTER);
        } else if (DBConstant.SLAVE01.equals(datasourceName)) {
            putDataSource(DataSourceType.SLAVE01);
        } else if (DBConstant.SLAVE02.equals(datasourceName)) {
            putDataSource(DataSourceType.SLAVE02);
        }
    }

    public static void setDbTypeByDatasourceName(String datasourceName) {
        putDataSource(datasourceName);
    }

    public static String getDataSource() {
        return local.get();
    }
}
