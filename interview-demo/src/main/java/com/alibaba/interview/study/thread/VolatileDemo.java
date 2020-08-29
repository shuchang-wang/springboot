package com.alibaba.interview.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData {
    //int number = 0;
    volatile int number = 0;

    public void addTo60() {
        this.number = 60;
    }

    //请注意，此时number前面加了volatile关键字修饰，volatile不保证原子性
    public void addPlusPlus1() {
        number++;
    }

    // 解决和最终结果不一致，方式一：方法上加synchronize同步关键字
    public synchronized void addPlusPlus2() {
        number++;
    }

    //解决和最终结果不一致，方式二：使用原子操作AutoMaticInteger
    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomic() {
        atomicInteger.getAndIncrement();
    }
}


/**
 * 1、验证volatitle的可见性
 *  1.1 假如 int number=0;number变量之前根本没有添加volatile关键字修饰
 *  1.2 添加volatile关键字，可以解决可见性问题。
 * <p>
 * 2、验证volatile不保证原子性
 *  2.1 原子性指的是什么意思？
 *      不可分割，完整性，也即某个线程正在做某个具体的业务时，中间不可以被加塞或者被分割。需要整体完整，要么同时成功，要么同时失败。
 *  2.2 volatile不保证原子性的案例演示
 *  2.3 volatile为什么不保证原子性？
 *     2.3.1 n++底层JVM源码实际，将拆分成三个步骤：先从主内存拷贝一份副本变量，再进行+1操作；最后把修改后的变量写回到主内存，这期间（写）可能产生非正常写入导致非原子性，从而最终结果和理想值不一样
 *  2.4 如何解决？
 *      2.4.1 在方法上加synchronize同步锁关键字修饰【杀鸡用了牛刀，有些大材小用了】
 *      2.4.2 使用并发包下的【java.util.concurrent——》JUC】AtomicInteger原子类型
 */
public class VolatileDemo {

    public static void main(String[] args) {
        //1、验证volatitle的可见性
        //seeOkByVolatile();

        //2.1不保证原子性，最终结果和理想值不一致
        //noAtomicVolatile();

        //2.2解决不保证原子性问题：方式1：synchronize
        noAtomicVolatitleSolve1();

        //2.3解决不保证原子性问题：方式1：AtomicInteger
        //noAtomicVolatitleSolve2();
    }

    public static void noAtomicVolatitleSolve2() {
        MyData mydata = new MyData();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    mydata.addAtomic();
                }
            }, "Thread-" + i).start();
        }

        //需要等待上面20个线程都全部计算完成后，再用main线程取得最终的结果值是多少。
        while (Thread.activeCount() > 2) {//为什么大于2呢？因为运行的线程启动运行必须有main线程和GC线程运行。
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "\t AtomicInteger type,finally number value=" + mydata.atomicInteger);
    }

    public static void noAtomicVolatitleSolve1() {
        MyData mydata = new MyData();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    mydata.addPlusPlus2();
                }
            }, "Thread-" + i).start();
        }

        //需要等待上面20个线程都全部计算完成后，再用main线程取得最终的结果值是多少。
        while (Thread.activeCount() > 2) {//为什么大于2呢？因为运行的线程启动运行必须有main线程和GC线程运行。
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "\t int type,finally number value=" + mydata.number);
    }

    public static void noAtomicVolatile() {
        MyData mydata = new MyData();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    mydata.addPlusPlus1();
                }
            }, "Thread-" + i).start();
        }

        //需要等待上面20个线程都全部计算完成后，再用main线程取得最终的结果值是多少。
        while (Thread.activeCount() > 2) {//为什么大于2呢？因为运行的线程启动运行必须有main线程和GC线程运行。
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "\t finally number value=" + mydata.number);
    }

    //volatile可以保证可见性，及时通知其他线程，朱物理内存的值已经被修改。
    public static void seeOkByVolatile() {
        MyData myData = new MyData();//资源类
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t come in");
                TimeUnit.SECONDS.sleep(2);
                myData.addTo60();
                System.out.println(Thread.currentThread().getName() + "\t updated number value：" + myData.number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();

        //main线程
        while (myData.number == 0) {
            //main线程就一直在这里等待循环，直到number值不再等于0
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over ,main get number value=" + myData.number);
    }

}