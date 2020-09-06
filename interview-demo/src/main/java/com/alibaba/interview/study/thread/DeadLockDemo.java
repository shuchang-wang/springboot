package com.alibaba.interview.study.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author: WSC
 * @Create 2020/9/6 18:32
 * 死锁：是指两个或者以上的进程在执行过程中，因争夺资源而造成的一种相互等待的现象，若无外力干涉那他们都将无法推进下去。
 *      死锁的产生：
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA, lockB), "ThreadAAA").start();
        new Thread(new HoldLockThread(lockB, lockA), "ThreadBBB").start();
        /**
         * linux        ps -ef | grep xxx       ls -l
         * windows下的java运行程序也有类似ps的查看进程的命令，但是目前我们需要查看的只是java
         *              jps  = java ps          jps -l
         */
    }
}

class HoldLockThread implements Runnable {
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t自己持有：" + lockA + "\t\t尝试获得：" + lockB);
            //暂停一会线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t自己持有：" + lockB + "\t\t尝试获得：" + lockA);
            }
        }

    }
}