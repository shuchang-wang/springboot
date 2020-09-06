package com.alibaba.interview.study.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author: WSC
 * @Create 2020/9/6 21:42
 *
 * jps      查看java的后台进程
 * jinfo processID   查看正在运行中的java的各种info信息
 *       jinfo -flag PrintGCDetails processID       查看jvm参数是否被激活使用
 *              $ jinfo -flag PrintGCDetails 10292c
 *                 -XX:+PrintGCDetails
 * jstack processID  打印当前java工作进程
 */
public class HellpGC {
    public static void main(String[] args) {
        System.out.println("**************Hello GC");
        //暂停一会线程
        try {
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);} catch (InterruptedException e) {e.printStackTrace();}
    }
}
