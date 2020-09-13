package com.alibaba.interview.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: WSC
 * @Create 2020/9/13 21:11
 * <p>
 * 生产者消费者模式案例、线程间通信案例
 * <p>
 * 题目：一个初始值为0的变量，两个线程对其交替操作，一个加1一个减1，来10轮
 * <p>
 * 企业级多线程的模板口诀：高内聚低耦合模式下，
 * 1、线程     操作（对外调用的方法）      资源类     【对联上联】
 * 2、判断     干活      通知（唤醒）                【对联下联】
 *      判断：判断是哪个线程来操作
 *      干活:线程要做的事情
 *      通知唤醒：唤醒其他线程
 * 3、防止虚假唤醒机制                              【对联横幅】
 *      【多线程环境使用while进行判断，不要使用if进行判断；否则存在虚假唤醒问题】
 */
public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {
        AirConditioner airConditioner = new AirConditioner();

        traditionalModel();
        //暂停一会线程
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("============================================");
        strongModel();
    }

    public static void strongModel() {
        AirConditioner airConditioner = new AirConditioner();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                airConditioner.increment();
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                airConditioner.decrement();
            }
        }, "BB").start();
    }

    public static void traditionalModel() {
        AirConditioner airConditioner = new AirConditioner();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {

                try {
                    airConditioner.increment0();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    airConditioner.decrement0();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {

                try {
                    airConditioner.increment0();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t3").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    airConditioner.decrement0();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t4").start();
    }
}

class AirConditioner {//资源类
    private int number = 0;

    //传统方式
    public synchronized void increment0() throws InterruptedException {//操作
        //判断
        while (number != 0) {//【多线程环境使用while进行判断，不要使用if进行判断；否则存在虚假唤醒问题】
            this.wait();
        }
        //干活
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //通知（唤醒）
        this.notifyAll();
    }
    public synchronized void decrement0() throws InterruptedException {//操作
        //判断
        while (number == 0) {//【多线程环境使用while进行判断，不要使用if进行判断；否则存在虚假唤醒问题】
            this.wait();
        }
        //干活
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //通知（唤醒）
        this.notifyAll();
    }

    //升级版方式
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void increment() {//操作
        lock.lock();
        try {
            //判断
            while (number != 0) {//【多线程环境使用while进行判断，不要使用if进行判断；否则存在虚假唤醒问题】
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //通知（唤醒）
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void decrement() {//操作
        lock.lock();
        try {
            //判断
            while (number == 0) {//【多线程环境使用while进行判断，不要使用if进行判断；否则存在虚假唤醒问题】
                condition.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //通知（唤醒）
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}