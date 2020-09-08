package com.alibaba.interview.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * @author: WSC
 * @Create 2020/9/6 11:59
 */

/**
 * RocketMQ 消息生产者
 * 懒汉式单例模式+双重校验锁机制
 */
@Component
public class MQProducer {

    @Value("${groupName}")
    static String groupName;
    @Value("${namesrvAddr}")
    static String namesrvAddr;

    private static volatile DefaultMQProducer defaultMQProducer = null;

    private MQProducer() {}

    //双重校验锁机制
    public static DefaultMQProducer getDefaultMQProducer() {
        if(defaultMQProducer==null){
            synchronized(MQProducer.class){
                if (defaultMQProducer == null) {
                    defaultMQProducer = new DefaultMQProducer(groupName);
                }
            }
        }
        return defaultMQProducer;
    }
}