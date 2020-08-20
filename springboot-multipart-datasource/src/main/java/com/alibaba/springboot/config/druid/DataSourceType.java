package com.alibaba.springboot.config.druid;

/**
 * @description: 数据源类型枚举
 */
public enum DataSourceType {

    // 主表
    MASTER("MASTER"),
    // 副库01
    SLAVE01("SLAVE01"),
    // 副库02
    SLAVE02("SLAVE02");

    private String name;

    private DataSourceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    }
