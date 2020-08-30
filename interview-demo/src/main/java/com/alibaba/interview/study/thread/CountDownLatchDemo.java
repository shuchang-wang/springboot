package com.alibaba.interview.study.thread;

import com.alibaba.interview.enums.CountryEnum;

import java.util.concurrent.CountDownLatch;

/**
 * @author: WSC
 * @Create 2020/8/30 23:20
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        //关门案例
        closeDoor();

        //秦灭六国案例
        gameOverSixCountry();
    }

    //秦灭六国案例
    public static void gameOverSixCountry() {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t国，被灭。");
                countDownLatch.countDown();
            }, CountryEnum.foreach_CountryEnum(i).getRetMessage()).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t###########秦帝国，统一华夏");
        System.out.println();
        System.out.println(CountryEnum.ONE);
        System.out.println(CountryEnum.ONE.getRetCode());
        System.out.println(CountryEnum.ONE.getRetMessage());
    }

    //关门案例
    public static void closeDoor() {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t上完自习，离开教室。");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t###########班长最后关门走人");
    }
}