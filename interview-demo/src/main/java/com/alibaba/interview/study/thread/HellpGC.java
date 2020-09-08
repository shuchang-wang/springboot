package com.alibaba.interview.study.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author: WSC
 * @Create 2020/9/6 21:42
 *第一种，查看JVM参数盘点家底
 *      jps      查看java的后台进程
 *      jinfo processID   查看正在运行中的java的各种info信息
 *          jinfo -flag PrintGCDetails processID       查看jvm参数是否被激活使用
 *                  $ jinfo -flag PrintGCDetails 10292c
 *                  -XX:+PrintGCDetails
 *  第二种，查看JVM参数盘点家底
 *      java -XX:+PrintFlagsInitial -version
 *      java -XX:+PrintFlagsFinal -version
 * jstack processID  打印当前java工作进程
 */
public class HellpGC {
    public static void main(String[] args) {
        System.out.println("**************Hello GC");
//        long totalMemory = Runtime.getRuntime().totalMemory();
//        long maxMemory = Runtime.getRuntime().maxMemory();
//        System.out.println("TOTAL_MEMPRY(-Xms)="+totalMemory+"(字节)、"+totalMemory/1024/1024+"MB");
//        System.out.println("MAX_MEMPRY(-Xmx)="+maxMemory+"(字节)、"+maxMemory/1024/1024+"MB");
        //暂停一会线程
        try {TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);} catch (InterruptedException e) {e.printStackTrace();}
//        byte[] arr = new byte[50 * 1024 * 1024];
    }
}
