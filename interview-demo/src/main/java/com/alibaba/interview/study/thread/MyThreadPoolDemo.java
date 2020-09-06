package com.alibaba.interview.study.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: WSC
 * @Create 2020/9/6 12:53
 * 第4种获得/使用java多线程的方式，线程池
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {
        //System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5个处理线程
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();//一池一个处理线程
        ExecutorService threadPool3 = Executors.newCachedThreadPool();//一次N个处理线程
        try {
            //模拟10个银行用户来办理业务，每个用户就是来自外部的请求线程
            for (int i = 1; i <= 100; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
                //暂停一会线程
               // try {TimeUnit.MILLISECONDS.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

}
