package com.alibaba.interview.study.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author: WSC
 * @Create 2020/8/31 22:27
 * SemaphoreDemo：信号量
 * 抢车位demo
 */
public class SemaphoreDemo {

    public static void main(String[] args) {

        //非公平锁
        Semaphore semaphore = new Semaphore(3);//模拟三个停车位
        //Semaphore semaphore = new Semaphore(3, false);//模拟三个停车位
        //公平锁
       //Semaphore semaphore = new Semaphore(3, true);//模拟三个停车位

        for (int i = 0; i < 6; i++) {//模拟6部车
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("第" + Thread.currentThread().getName() + "号幸运车主，抢到了车位！");
                    try {
                        //暂停3秒钟
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("第" + Thread.currentThread().getName() + "号幸运车主，停了3秒钟离开车位！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }

    }
}