package com.alibaba.interview.study.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: WSC
 * @Create 2020/8/30 10:42
 *  CAS:CompareAndSwap【比较并交换】
 */
public class CASDemo {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2020)+"\t   current data:  "+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1024)+"\t   current data:  "+atomicInteger.get());

    }

}
