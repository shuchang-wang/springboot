package com.alibaba.interview.study.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: WSC
 * @Create 2020/9/5 19:49
 * 题目：synchronized和Lock有什么区别？用新的Lock有什么好处？举例说说？
 * 1、原始构成
 *      synchronized是关键字属于JVM层面
 *          monitorenter（底层是通过monitor对象来完成，其实wait/notify等方法也依赖于monitor对象，只有在同步块或方法中方法中才能调用wait/notify等方法）
 *          monitorexit
 *      Lock是具体类（java.util.concurrent.locks.Lock）是api层面的锁。
 *
 *  2、使用方法
 *      synchronized不需要用户手动释放锁，当synchronized代码执行完抽系统会自动让线程释放对锁的作用
 *      ReentrantLock则需要用户去手动释放锁，若没有主动释放锁，就有可能导致出现死锁现象。需要lock()和unlock()方法配合try/finally语句块来完成。
 *
 * 3、等待是否可中断
 *      synchronized不可中断，除非抛出异常或者正常运行完成
 *      ReentrantLock可中断，
 *          1.设置超时方法tryLock(long time, TimeUnit unit)
 *          2.lockInterruptibly()方代码块中，调用interrupt()方法可中断
 * <p>
 * 4、加锁是否公平
 *      synchronized非公平锁
 *      ReentrantLock两者都可以，默认非公平锁，构造方法可转入boolean值，true为公平锁，false为非公平锁
 * <p>
 * 5、锁绑定多个条件Condition
 *      synchronized没有
 *      ReentrantLock用来实现分组唤醒需要唤醒的线程们，可以精确唤醒，而不是像synchronized要么随机唤醒一个线程要么唤醒全部线程。
 * ====================================================================================================================
 * 题目：多线程之间按顺序调用，实现A->B->C三个线程启动，要求如下：
 * AA打印5次，BB打印10次，CC打印15次
 * 紧接着
 * AA打印5次，BB打印10次，CC打印15次
 * ......
 * 来10轮
 */
public class SyncAndReentrantLockDemo {

    public static void main(String[] args) {
        synchronized (SyncAndReentrantLockDemo.class){

        }
        new ReentrantLock();
//        ShareResource resource = new ShareResource();
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                resource.print5();
//            }, "A").start();
//            new Thread(() -> {
//                resource.print10();
//            }, "B").start();
//            new Thread(() -> {
//                resource.print15();
//            }, "C").start();
//        }
    }
}

class ShareResource {
    private int number = 1;//标志位    A:1、B:2、C:3
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            //判断
            while (number != 1) {
                condition1.await();
            }
            //干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number = 2;
            //通知
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            //判断
            while (number != 2) {
                condition2.await();
            }
            //干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number = 3;//重置标志位
            //通知
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            //判断
            while (number != 3) {
                condition3.await();
            }
            //干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number = 1;//重置标志位
            //通知
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}