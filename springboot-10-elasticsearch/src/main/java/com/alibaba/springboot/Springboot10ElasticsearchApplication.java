package com.alibaba.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot默认支持两种技术与ES交互
 * 1、Jest(默认不生效)
 * 		需要导入jest的工具包（io.searchbox.client.JestClient）
 * 2、SpringData ElasticSearch
 * 		1）、Client	需要配置这些参数信息：节点信息clusterNodes、clusterName
 * 		2）、ElasticsearchTemplate操作ES
 * 		3）、编写一个ElasticsearchRepository的子接口来操作ES
 */
@SpringBootApplication
public class Springboot10ElasticsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot10ElasticsearchApplication.class, args);
	}

}
