package com.alibaba.interview.study.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//T1.java————》T1.class————》JVM字节码
public class T1 {

    volatile int n = 0;

    public void add(){
        n++;
    }

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();//非公平锁==new ReentrantLock(false)
        Lock lock1 = new ReentrantLock(true);//公平锁
    }
}
