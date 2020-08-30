package com.alibaba.interview.study.thread;

public class SingletonDemo {

    private static volatile SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法SingletomDemo()");
    }

    //单线程OK，多线程有问题
    public static SingletonDemo getInstance1() {
        //单例模式：懒汉式【单线程环境OK，多线程环境会有问题】
        if (instance == null) {
            instance = new SingletonDemo();
        }
        return instance;
    }

    //单线程和多线程都OK，但锁的重量太重，是重量级锁【不推荐】
    public static synchronized SingletonDemo getInstance2() {
        if (instance == null) {
            instance = new SingletonDemo();
        }
        return instance;
    }

    //双重校验锁机制（Double Check Lock——》DCL锁），是轻量级锁【由于没有volatile修饰，也可能存在问题】单线程OK，多线程下大部分情况下是OK的
    public static SingletonDemo getInstance3() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    //双重校验锁机制（Double Check Lock——》DCL锁），是轻量级锁【推荐】变量加入volatile修饰，单线程和多线程都OK
    public static SingletonDemo getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        //单线程环境（main线程的操作动作）
        //System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());//true
        //System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());//true
        //System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());//true
        System.out.println();

        //并发多线程后，情况发生了很大变化
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            }, "Thread-" + String.valueOf(i)).start();
        }
    }

}
