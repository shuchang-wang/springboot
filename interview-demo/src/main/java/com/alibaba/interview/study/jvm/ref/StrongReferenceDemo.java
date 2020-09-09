package com.alibaba.interview.study.jvm.ref;

/**
 * @author: WSC
 * @Create 2020/9/8 21:41
 * 强引用：当内存不足，JVM开始垃圾回收，对于强应用的对象，就算是出现了OOM也不会对该对象进行回收，死都不回收。
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object obj1 = new Object();//这样定义的默认就是强引用
        Object obj2 = obj1;//obj2引用赋值
        obj1 = null;//置空
        System.gc();
        System.out.println(obj2);//obj2不为空，打印结果为：java.lang.Object@13221655


    }
}
