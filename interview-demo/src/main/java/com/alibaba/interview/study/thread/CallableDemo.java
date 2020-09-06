package com.alibaba.interview.study.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author: WSC
 * @Create 2020/9/6 10:31
 * 题目：多线程实现方式有几种？4种
 *      1、继承Thread类，重写run方法。
 *      2、实现Runnable接口，重写run方法。
 *      3、实现Callable接口，重写call方法
 *      4、线程池创建多线程方式
 * <p>
 * <p>
 * 题目：Callable和Runnable接口的区别？
 *      1、Callable接口方法有返回值，而Runnable接口无返回值。
 *      2、Callable接口可以抛出异常，而Runnable接口不可以抛出异常。
 *      3、Callable接口的方法是call()方法，而Runnable接口的方法是run()方法。
 *          使用：
 *              FutureTask<Integer> futureTask2 = new FutureTask<Integer>(new MyThreadCallable());
 *              new Thread(futureTask, "AA").start();
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //两个线程，一个main线程，一个是AA线程
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyThreadCallable());
        FutureTask<Integer> futureTask2 = new FutureTask<Integer>(new MyThreadCallable());
        new Thread(futureTask, "AA").start();
        new Thread(futureTask, "BB").start();//多个线程来抢一个Future，FutureTask只计算一次。不执行，将会执行一次。
        new Thread(futureTask2, "CC").start();//如果非要强制在执行一次，则必须新建FutureTask

        int result01 = 100;
//        while(!futureTask.isDone()){
//
//        }
        int result02 = futureTask.get();//【建议get()方法放到最后，调用该方法会当前阻塞线程】要求获得Callable线程的计算结果，如果没有计算完成就要去强求，会导致堵塞，直到计算完成。
        int result03 = futureTask2.get();//【建议get()方法放到最后，调用该方法会当前阻塞线程】要求获得Callable线程的计算结果，如果没有计算完成就要去强求，会导致堵塞，直到计算完成。
        System.out.println(Thread.currentThread().getName() + "\t*********************result=" + (result01 + result02));
        System.out.println(Thread.currentThread().getName() + "\t*********************result=" + (result01 + result03));
    }

}

/**
 * 多线程实现方式：继承Thread类，重写run方法
 */
class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "\t********************come in Runable接口");
    }

}

/**
 * 多线程实现方式：实现Runnable接口，重写run方法
 */
class MyThreadRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "\t********************come in Runable接口");
    }

}

/**
 * 多线程实现方式：实现Callable接口,重写call()方法
 */
class MyThreadCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t********************come in Callable接口");
        //暂停一会线程
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1024;
    }
}