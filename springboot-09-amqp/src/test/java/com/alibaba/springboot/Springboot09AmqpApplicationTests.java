package com.alibaba.springboot;

import com.alibaba.springboot.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot09AmqpApplicationTests {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	AmqpAdmin amqpAdmin;

	@Test
	public void createExchange(){
//		amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
//		System.out.println("创建完成");
//		amqpAdmin.declareQueue(new Queue("amqp.queue",true));
//		创建绑定规则
		amqpAdmin.declareBinding(new Binding("amqp.queue", Binding.DestinationType.QUEUE,"amqpadmin.exchange","amqp.haha",null));
	}

	/**
	 * 1、单播（点对点）
	 */
	@Test
	public void send() {
		//Message需要自己构造一个；定义消息体内容和消息头
		//rabbitTemplate.send(exchange,routingKey,message);

		//object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq；
		//rabbitTemplate.convertAndSend(exchange,routingKey,Object);
		Map<String,Object> map = new HashMap<>();
		map.put("msg","第一个消息");
		map.put("data", Arrays.asList("helloworld",123,true));
		//对象被默认序列化以后发送出去
		//rabbitTemplate.convertAndSend("exchange.direct","alibaba.news",map);
		rabbitTemplate.convertAndSend("exchange.direct","alibaba.news",new Book("西游记","吴承恩"));
	}

	/**
	 * 接收数据
	 */
	@Test
	public void receive(){
		Object o = rabbitTemplate.receiveAndConvert("alibaba.news");
		System.out.println(o.getClass());
		System.out.println(o);
	}

	/**
	 * 广播
	 */
	@Test
	public void send02() {
//		rabbitTemplate.convertAndSend("exchange.fanout","",new Book("三国演义","罗贯中"));
		rabbitTemplate.convertAndSend("exchange.fanout","",new Book("红楼梦","曹雪芹"));
	}
}

























