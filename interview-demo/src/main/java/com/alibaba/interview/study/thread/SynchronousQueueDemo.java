package com.alibaba.interview.study.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author: WSC
 * @Create 2020/9/5 16:04
 * 阻塞队列SynchronousQueue演示
 * SynchronousQueue没有容量，与其他BlcokingQueue不同，SynchronousQueue是一个不存储元素的BlcokingQueue。
 * 每个put操作必须要等待一个take操作，否则不能继续添加元素，反之亦然。
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {
             try {
                 System.out.println(Thread.currentThread().getName()+"\t put 1");
                 blockingQueue.put("1");
                 System.out.println(Thread.currentThread().getName()+"\t put 2");
                 blockingQueue.put("2");
                 System.out.println(Thread.currentThread().getName()+"\t put 3");
                 blockingQueue.put("3");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }, "AAA").start();

        new Thread(() -> {
            try {
                //暂停5秒钟
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"\t get "+blockingQueue.take());
                //暂停5秒钟
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"\t get "+blockingQueue.take());
                //暂停5秒钟
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"\t get "+blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BBB").start();
    }
}
