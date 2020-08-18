package com.alibaba.springboot;

import com.alibaba.springboot.bean.Article;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot10ElasticsearchApplicationTests {

    @Autowired
    private JestClient jestClient;

    @Test
    public void test01() {
        //1、给ES中索引（保存）一个文档
        Article article = new Article(1, "zhangsan", "好消息", "Hello-World");

        //构建一个索引功能
        Index index = new Index.Builder(article).index("alibaba").type("news").build();
        try {
            //执行
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //测试ES搜索
    @Test
    public void test02() {
        String json = "{\n" +
                "    \"query\": {\n" +
                "        \"match\": {\n" +
                "            \"content\": \"Hello\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        Search search = new Search.Builder(json).addIndex("alibaba").addType("news").build();
        try {
            SearchResult searchResult = jestClient.execute(search);
            System.out.println(searchResult.getMaxScore());
            System.out.println(searchResult.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
