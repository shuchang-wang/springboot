package com.alibaba.springboot.repository;

import com.alibaba.springboot.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookRepository extends ElasticsearchRepository<Book, Integer> {
    //方法命名规则参照：https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.query-methods.criterions
    public List<Book> findBookByBookNameLike(String bookName);
}
