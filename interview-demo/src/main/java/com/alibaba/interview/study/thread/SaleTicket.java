package com.alibaba.interview.study.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: WSC
 * @Create 2020/9/13 19:36
 * <p>
 *  模拟火车票售票30张
 *
 * 多线程企业级编程模板
 * <p>
 * 1、在高内聚低耦合的前提下；线程     操作      资源类
 * 2、判断     干活      通知唤醒
 * 3、防止虚假唤醒机制
 */
class Ticket {
    private int number = 30;
//    public synchronized void saleTicket(){
//        if(number>0){//有票可以继续出售。
//            System.out.println(Thread.currentThread().getName()+"卖出第"+number--+"票，还剩"+number+"张票");
//        }
//    }

    private Lock lock = new ReentrantLock();
    public void saleTicket() {
        lock.lock();
        try {
            if (number > 0) {//有票可以继续出售。
                System.out.println(Thread.currentThread().getName() + "卖出第" + number-- + "票，还剩" + number + "张票");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 40; i++) {
//                    ticket.saleTicket();
//                }
//            }
//        }, "t1").start();
//        ;
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 40; i++) {
//                    ticket.saleTicket();
//                }
//            }
//        }, "t2").start();
//        ;
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 40; i++) {
//                    ticket.saleTicket();
//                }
//            }
//        }, "t3").start();

        //Lamda表达式写法
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.saleTicket();
            }
        }, "t4").start();
        //Lamda表达式写法
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.saleTicket();
            }
        }, "t5").start();
        //Lambda表达式写法
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.saleTicket();
            }
        }, "t6").start();
    }
}
