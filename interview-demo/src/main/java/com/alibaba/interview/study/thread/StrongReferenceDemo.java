package com.alibaba.interview.study.thread;

/**
 * @author: WSC
 * @Create 2020/9/8 21:41
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object obj1 = new Object();//这样定义的默认就是强引用
        Object obj2 = obj1;//obj2引用赋值
        obj1 = null;//置空
        System.gc();
        System.out.println(obj2);

    }
}
