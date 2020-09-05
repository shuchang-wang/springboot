package com.alibaba.interview.study.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: WSC
 * @Create 2020/9/5 18:01
 *  题目：一个初始值为0的变量，两个线程对其交替操作，一个加1一个减1，来5轮
 *
 * 企业级多线程的模板口诀：高内聚低耦合模式下，
 *  1、线程     操作（方法）      资源类    【对联上联】
 *  2、判断     干活      通知唤醒         【对联下联】
 *      判断：判断是哪个线程来操作
 *      干活:线程要做的事情
 *      通知唤醒：唤醒其他线程
 *  3、防止虚假唤醒机制                    【对联横幅】
 *      【多线程环境使用while进行判断，不要使用if进行判断；否则存在问题】
 */
public class ProdConsumer_TranditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    shareData.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AA").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    shareData.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }
}

//资源类
class ShareData {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (number != 0) {//while判断，防止虚假唤醒。 //多线程环境判断一定要用while，不能用if进行判断，否则产生错误
                //等待，不能生产
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //通知唤醒
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (number == 0) {//while判断，防止虚假唤醒。
                //等待，不能消费
                condition.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //通知唤醒
            condition.signalAll();
            //暂停3秒钟
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}