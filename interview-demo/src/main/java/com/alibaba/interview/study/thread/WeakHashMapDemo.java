package com.alibaba.interview.study.thread;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @author: WSC
 * @Create 2020/9/8 23:28
 * java.util.WeakHashMap 只要发生GC，就回收
 */
public class WeakHashMapDemo {
    public static void main(String[] args) {
        myHashMap();
        System.out.println("==========================");
        myWeakHashMap();
    }

    /**
     * 只要发生GC，就回收
     */
    private static void myWeakHashMap() {
        WeakHashMap<Integer,String> weakHashMap = new WeakHashMap<>();
        Integer key = new Integer(2);
        String value = "HashMap";
        weakHashMap.put(key,value);
        System.out.println(weakHashMap);

        key = null;
        System.out.println(weakHashMap);

        System.gc();
        System.out.println(weakHashMap+"\t"+weakHashMap.size());
    }

    private static void myHashMap() {
        HashMap<Integer,String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "HashMap";
        map.put(key,value);
        System.out.println(map);
        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map+"\t"+map.size());
    }
}
