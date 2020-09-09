package com.alibaba.interview.study.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: WSC
 * @Create 2020/8/30 23:21
 * 聚齐七龙珠召唤神龙
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() -> {
            System.out.println("*******************七龙珠聚齐，开始召唤神龙*******************");
        });
        for (int i = 1; i <= 7; i++) {
            new Thread(() -> {
                try {
                    System.out.println("收集到编号为"+Thread.currentThread().getName()+"的龙珠");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}