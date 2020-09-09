package com.alibaba.interview.study.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: WSC
 * @Create 2020/8/30 20:23
 *
 * 可重入锁（也叫做递归锁）
 * 指的是同一个线程外层函数获得锁之后，内层递归仍然能获取该锁的代码，
 * 在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁。
 *   也就是说，线程可以进入任何一个它已经拥有的锁所同步着的代码块。
 *case one synchronized 是可重入锁
 * t1	 invoked sendSMS()  //在同一个线程在外层方法获取锁的时候
 * t1	 #############invoked sendEmail()   //在进入内层方法会自动获取锁
 *
 * t2	 invoked sendSMS()
 * t2	 #############invoked sendEmail()
 *
 *case two ReentrantLock 是可重入锁
 * t3	 invoked get()
 * t3	 #####invoked set()
 * t4	 invoked get()
 * t4	 #####invoked set()
 */
public class ReentrantLockDemo{
    public static void main(String[] args) {
        Phone phone = new Phone();
        //synchronized可重入锁
        synchronizedReentrant(phone);

        System.out.println();
        System.out.println();

        //RreentrantLock可重入锁
        reentrantLockReentrant(phone);
    }

    public static void reentrantLockReentrant(Phone phone) {
        Thread t3 = new Thread(phone,"t3");
        Thread t4 = new Thread(phone,"t4");
        t3.start();
        t4.start();
    }

    public static void synchronizedReentrant(Phone phone) {
        new Thread(()->{
            phone.sendSMS();
        },"t1").start();

        new Thread(()->{
            phone.sendSMS();
        },"t2").start();
    }

}

class Phone implements Runnable{
    public  synchronized void sendSMS(){
        System.out.println(Thread.currentThread().getName()+"\t invoked sendSMS()");
        sendEmail();
    }

    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName()+"\t #############invoked sendEmail()");
    }

    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    public void get(){
        //加几次锁，必须释放几次锁；两两配对
        lock.lock();
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t invoked get()");
            set();
        }finally {
            lock.unlock();
            lock.unlock();
        }
    }
    public void set(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t #####invoked set()");
        }finally {
            lock.unlock();
        }
    }
}