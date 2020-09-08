package com.alibaba.interview.study.thread;

import java.lang.ref.WeakReference;

/**
 * @author: WSC
 * @Create 2020/9/8 22:53
 * WeakReference:软引用；对于只有弱引用的对象来说，只要垃圾回收机制一运行，不管JVM的内存空间是否够用，都会回收该对象占用的内存。
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object object = new Object();
        WeakReference<Object> weakReference = new WeakReference<Object>(object);
        System.out.println(object);//java.lang.Object@13221655
        System.out.println(weakReference.get());//java.lang.Object@13221655
        object = null;
        System.gc();
        System.out.println("=============================");
        System.out.println(object);//null
        System.out.println(weakReference.get());//null
    }
}
