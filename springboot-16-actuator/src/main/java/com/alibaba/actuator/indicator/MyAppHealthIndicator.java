package com.alibaba.actuator.indicator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component  //加入容器
public class MyAppHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        //自定义的检查方法
        //Health.up().build();
        return Health.down().withDetail("msg", "服务异常").build();
    }
}