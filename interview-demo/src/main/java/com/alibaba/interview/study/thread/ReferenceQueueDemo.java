package com.alibaba.interview.study.thread;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author: WSC
 * @Create 2020/9/9 20:36
 */
public class ReferenceQueueDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<Object>(o1,referenceQueue);
        System.out.println(o1);//java.lang.Object@13221655
        System.out.println(weakReference.get());//java.lang.Object@13221655
        System.out.println(referenceQueue.poll());//null

        System.out.println("=============================================");
        o1 = null;
        System.gc();
        System.out.println(o1);//null
        System.out.println(weakReference.get());//null
        System.out.println(referenceQueue.poll());//java.lang.ref.WeakReference@2f2c9b19
    }
}
