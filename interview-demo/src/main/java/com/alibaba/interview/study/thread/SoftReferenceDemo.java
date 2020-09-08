package com.alibaba.interview.study.thread;

import java.lang.ref.SoftReference;

/**
 * @author: WSC
 * @Create 2020/9/8 22:15
 * SoftReference软引用：内存够用的时候就保留，不够用就回收！
 */
public class SoftReferenceDemo {
    public static void softRef_Memory_Enough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<Object>(o1);
        System.out.println(o1);//java.lang.Object@13221655
        System.out.println(softReference.get());//java.lang.Object@13221655

        o1 = null;
        System.gc();
        System.out.println(o1);//null
        System.out.println(softReference.get());//java.lang.Object@13221655
    }

    /**
     * JVM配置，故意产生大对象并设置小的内存，让它内存不够用了OOM，看软引用的回收情况
     * -Xms10m -Xmx10m -XX:+PrintGCDetails
     */
    public static void softRef_Memory_NotEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<Object>(o1);
        System.out.println(o1);//java.lang.Object@13221655
        System.out.println(softReference.get());//java.lang.Object@13221655

        o1 = null;

        try {
            byte[] bytes = new byte[30 * 1024 * 1024];
        } catch (Exception e) {
            // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
            e.printStackTrace();
        } finally {
            System.out.println("finally;\t o1="+o1);//null
            System.out.println("finally;\t softReference="+softReference.get());//null
        }
    }

    public static void main(String[] args) {
        //内存够用不回收
        softRef_Memory_Enough();
        System.out.println("=================================");
        //内存够用回收
        softRef_Memory_NotEnough();
    }
}
