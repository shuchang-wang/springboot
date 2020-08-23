package com.alibaba.springboot;

import com.alibaba.springboot.config.druid.JdbcContextHolder;
import com.alibaba.springboot.constant.DBConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMultipartDatasourceApplicationTests {

    @Test
    public void contextLoads() throws SQLException {
        String dataSource = JdbcContextHolder.getDataSource();
        JdbcContextHolder.setDbTypeByDatasourceName(DBConstant.MASTER);
        System.out.println(JdbcContextHolder.getDataSource());
        JdbcContextHolder.setDbTypeByDatasourceName(DBConstant.SLAVE01);
        System.out.println(JdbcContextHolder.getDataSource());
        JdbcContextHolder.setDbTypeByDatasourceName(DBConstant.SLAVE02);
        System.out.println(JdbcContextHolder.getDataSource());
    }

    // 遍历后判断赋给另一个list集合，保持原来顺序
    public List<String> listToDistnct01(List<String> list) {
        List<String> newList = new ArrayList<>();
        for (String item : list) {
            if (!newList.contains(item)) {
                newList.add(item);
            }
        }
        return newList;
    }

    // set集合去重，保持原来顺序
    public List<String> listToDistnct02(List<String> list) {
        Set<String> set = new HashSet<String>();
        List<String> newList = new ArrayList<>();
        for (String item : list) {
            if (set.add(item)) {
                newList.add(item);
            }
        }
        return newList;
    }

    // Set去重     由于Set的无序性，不会保持原来顺序
    public List<String> listToDistnct03(List<String> list) {
        Set<String> set = new HashSet<String>();
        List<String> newList = new ArrayList<>();
        set.addAll(list);
        newList.addAll(set);
        return newList;
    }

    // Set去重     由于Set的无序性，不会保持原来顺序
    public List<String> listToDistnct04(List<String> list) {
        List<String> newList = new ArrayList<>(new HashSet<String>(list));
        return newList;
    }

    // 利用LinkedHashSet去重并保持原顺序
    public List<String> listToDistnct05(List<String> list) {
        List<String> newList = new ArrayList<>(new LinkedHashSet<>(list));
        return newList;
    }

    //利用java8新特性去重
    public List<String> listToDistnct06(List<String> list) {
        List<String> newList = list.stream().distinct().collect(Collectors.toList());
        return newList;
    }


    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        list.add("5");
        list.add("6");
        list.add("1");
        list.add("2");
        list.add("1");
        list.add("3");
        list.add("4");
        List<String> listToDistnct01 = listToDistnct01(list);
        System.out.println(listToDistnct01);//[5, 6, 1, 2, 3, 4]
        List<String> listToDistnct02 = listToDistnct02(list);
        System.out.println(listToDistnct02);//[5, 6, 1, 2, 3, 4]
        List<String> listToDistnct03 = listToDistnct03(list);
        System.out.println(listToDistnct03);//[1, 2, 3, 4, 5, 6]
        List<String> listToDistnct04 = listToDistnct04(list);
        System.out.println(listToDistnct04);//[1, 2, 3, 4, 5, 6]
        List<String> listToDistnct05 = listToDistnct05(list);
        System.out.println(listToDistnct05);//[5, 6, 1, 2, 3, 4]
        List<String> listToDistnct06 = listToDistnct06(list);
        System.out.println(listToDistnct06);//[5, 6, 1, 2, 3, 4]
    }

}