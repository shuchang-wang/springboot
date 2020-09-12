package com.alibaba.interview.study.jvm.gc;

import java.util.Random;

/**
 * @author: WSC
 * @Create 2020/9/10 23:35
 *
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC
 * Heap
 * garbage-first heap   total 10240K, used 4773K [0x00000000ff600000, 0x00000000ff700050, 0x0000000100000000)
 *      region size 1024K, 1 young (1024K), 0 survivors (0K)
 * Metaspace       used 3283K, capacity 4496K, committed 4864K, reserved 1056768K
 *      class space    used 354K, capacity 388K, committed 512K, reserved 1048576K
 */
public class G1Demo {
    public static void main(String[] args) {
        System.out.println("*********************GCDemo hello");
        try {
            String str = "alibaba";
            while (true) {
                str += str + new Random().nextInt(666666) + new Random().nextInt(88888888);
                str.intern();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
