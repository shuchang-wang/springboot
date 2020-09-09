package com.alibaba.interview.study.jvm.oom;

import java.util.Random;

/**
 * @author: WSC
 * @Create 2020/9/9 22:07
 *  OOM:
 *      Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 */
public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
//        String str = "are you readly JavaHeapSpaceError";
//        while (true) {
//            str += str+new Random().nextInt(1111111)+new Random().nextInt(2222222);
//            str.intern();
//        }
        //-Xms10m -Xmx10m
        byte[] bytes = new byte[80 * 1024 *1024];
    }
}
