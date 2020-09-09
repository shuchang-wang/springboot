package com.alibaba.interview.study.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author: WSC
 * @Create 2020/8/31 23:28
 * ArrayListBlockingQueue：是一个基于数组结构的有界队列，此队列按FIFO（先进先出）原则对元素进行排序。
 * LinkedBlockingQueue：是一个基于链表结构的阻塞队列，此队列按FIFO（先进先出）排序元素，吞吐量通常要高于ArrayBlockQueue。
 * SynchronousQueue：是一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于
 * <p>
 * 1、队列
 * <p>
 * 2、阻塞队列
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        //List<String> list = null;
//        exceptionBlockingQueueMethod();
        System.out.println("=======================================");
//        noExceptionBlockingQueueMethod();
        System.out.println("=======================================");
        //blockBlockingQueueMethod();
        System.out.println("=======================================");
        timeoutExitBlockingQueueMethod();

    }

    /**
     * 超时退出:
     * 当阻塞队列满时，队列会阻塞生产者线程一定时间，超过限时后生产者线程就会退出。
     * 当阻塞队列空时，队列会阻塞消费者线程一定时间，超过限时后消费者线程就会退出。
     * offer("a", 2L, TimeUnit.SECONDS)
     * poll(3L, TimeUnit.SECONDS)
     * @throws InterruptedException
     */
    public static void timeoutExitBlockingQueueMethod() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("d", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3L, TimeUnit.SECONDS));
    }

    /**
     * 一直阻塞:
     * put()-当阻塞队列满时，生产者线程继续往队列里面put元素，队列会一直阻塞生产线程直到put数据or响应中断退出。
     * take()-当阻塞队列空时，消费者线程试图从队列take元素，队列会一直阻塞消费者线程直到队列可用。
     *
     * @throws InterruptedException
     */
    public static void blockBlockingQueueMethod() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        //blockingQueue.put("d");//当队列满时，再插入数据时会进入阻塞，直至插入成功
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        //System.out.println(blockingQueue.take());//当队列为空时，从队列中取数据将会发生阻塞，直至获取到
    }

    /**
     * 特殊值:
     * 插入方法，成功返回true；失败返回false
     * 移除方法，成功返回出队列的元素，队列里面没有就返回null
     * <p>
     * offer()-当阻塞队列满时，会添加失败返回false且不报异常
     * poll()-当阻塞队列为空时，获取将会返回null值且不报异常
     * peek()-当队列为空时，判断获取时将会返回null值且不报异常
     */
    public static void noExceptionBlockingQueueMethod() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));//当队列满时，不会报异常
        System.out.println(blockingQueue.peek());//检测阻塞队列是否为空，并获取队首的值但不移除该元素。
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.peek());
    }

    /**
     * 抛出异常:
            * 当阻塞队列满时，再往队列里面add插入元素会抛IllegalStateException: Queue full
     * 当阻塞队列空时，再往队列Remove元素时候回抛出NoSuchElementException
     * <p>
     * add()-当阻塞队列满的时候，再往队列里添加将会报异常：java.lang.IllegalStateException: Queue full
     * remove()-当阻塞队列空的时候，再从队列里获取元素将会报异常：java.util.NoSuchElementException
     * element()-当阻塞队列为空时，调用会报异常：ava.util.NoSuchElementException
     */
    public static void exceptionBlockingQueueMethod() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        //System.out.println(blockingQueue.add("d"));//java.lang.IllegalStateException: Queue full
        System.out.println(blockingQueue.element());//检测阻塞队列是否为空，并获取队首的值但不移除该元素。
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //System.out.println(blockingQueue.remove());//java.util.NoSuchElementException
        //System.out.println(blockingQueue.element());//java.util.NoSuchElementException
    }

}