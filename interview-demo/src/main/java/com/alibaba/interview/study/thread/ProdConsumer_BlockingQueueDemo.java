package com.alibaba.interview.study.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: WSC
 * @Create 2020/9/5 22:37
 * volatile/CAS/atomicInteger/BlockingQueue/线程交互/原子引用
 */
class MyResource {
    /**
     * 默认开启 进行生产消费的交互
     */
    private volatile boolean FLAG = true;
    /**
     * 默认值是0
     */
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue blockingQueue = null;

    //传接口而非实现类，是为了可扩展性和复用性
    public MyResource(BlockingQueue blockingQueue) {
        System.out.println(blockingQueue.getClass().getName());
        this.blockingQueue = blockingQueue;
    }

    public void myProd() {
        String data = null;
        boolean result = false;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            try {
                result = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
                if (result) {
                    System.out.println(Thread.currentThread().getName() + "\t插入队列" + data + "成功");
                } else {
                    System.out.println(Thread.currentThread().getName() + "\t插入队列" + data + "失败");
                }
                //暂停一会线程
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "\t大老板叫停了，标识FLAG=false，生产动作结束！");
    }

    public void myConsumer() {
        String result = "";
        while (FLAG) {
            try {
                result = (String) blockingQueue.poll(2L, TimeUnit.SECONDS);
                if (result == null || "".equals(result)) {
                    FLAG = false;
                    System.out.println(Thread.currentThread().getName() + "\t超过2秒钟没有取到蛋糕，消费退出");
                    System.out.println();
                    System.out.println();
                    return;
                }
                System.out.println(Thread.currentThread().getName() + "\t消费队列蛋糕" + result + "成功");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        this.FLAG = false;
    }
}

public class ProdConsumer_BlockingQueueDemo {

    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue(10));
        //MyResource myResource = new MyResource(new SynchronousQueue());
        //MyResource myResource = new MyResource(new LinkedBlockingQueue());

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "生产线程启动");
            System.out.println();
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Prod").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "消费线程启动");
            System.out.println();
            try {
                myResource.myConsumer();
                System.out.println();
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Consumer").start();

        //暂停一会线程
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println("5秒钟时间到，大老板" + Thread.currentThread().getName() + "线程叫停，活动结束！");
        myResource.stop();
    }
}
