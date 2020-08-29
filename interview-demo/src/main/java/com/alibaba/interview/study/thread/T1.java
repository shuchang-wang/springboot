package com.alibaba.interview.study.thread;

//T1.java————》T1.class————》JVM字节码
public class T1 {

    volatile int n = 0;

    public void add(){
        n++;
    }
}
