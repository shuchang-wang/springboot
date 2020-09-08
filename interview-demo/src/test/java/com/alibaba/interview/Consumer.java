package com.alibaba.interview;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author: WSC
 * @Create 2020/9/1 17:36
 */
public class Consumer {

    /**
     * DefaultMQPushConsumer需要设置三个参数 : 一是这个Consumer的GroupName，二是NameServer的地址和端口号，三是Topic的名称。
     （1) Consumer的GroupName用于把多个Consumer组织到一起，提高并发处理能力，GroupName需要和消息模式 (MessageModel)配合使用。
         RocketMQ支持两种消息模式: Clustering和Broadcasting。
             在 Clustering 模式下，同一个ConsumerGroup(GroupName相同)里的每个Consumer只消费所订阅消息的一部分内容，
                同一个ConsumerGroup里所有的Consumer消费的内容合起来才是所订阅 Topic 内容的整体， 从而达到负载均衡的目的 。
             在 Broadcasting 模式下，同一个ConsumerGroup里的每个Consumer都能消费到所订阅Topic的全部消息，也就是一个消息会被多次分发，被多个Consumer消费。
     （2）NameServer 的地址和端口 号，可以填写多个 ，用分号隔开，达到消除单点故障的目的，比如 “ip1:port;ip2:port;ip3 :port” 。
     （3）Topic名称用来标识消息类型， 需要提前创建。如果不需要消费某 个 Topic 下的所有消息，可以通过指定消息的 Tag 进行消息过滤，
        比如: Consumer.subscribe (”TopicTest”，’tag1 || tag2 || tag3”)， 表示这个 Consumer要 消费“ TopicTest”下带有 tag1 或 tag2 或 tag3 的消息。
        在填写 Tag 参数的位置，用 null 或者“ *“ 表示要消费这个 Topic 的所有消息 。
     * @param args
     * @throws InterruptedException
     * @throws MQClientException
     */
    public static void main(String[] args) throws InterruptedException, MQClientException {

        // 实例化消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test_group");

        // 设置NameServer的地址
        consumer.setNamesrvAddr("192.168.21.152:9876");
        consumer.setNamesrvAddr("name-server1-ip:9876;name-server2-ip:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        // 订阅一个或者多个Topic，以及Tag来过滤需要消费的消息
        consumer.subscribe("TopicTest", "*");
        // 注册回调实现类来处理从broker拉取回来的消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                // 标记该消息已经被成功消费
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 启动消费者实例
        consumer.start();
        consumer.fetchSubscribeMessageQueues("TopicTest");
        System.out.printf("Consumer Started.%n");
    }

}