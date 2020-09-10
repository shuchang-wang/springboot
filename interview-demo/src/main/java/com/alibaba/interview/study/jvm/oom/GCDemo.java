package com.alibaba.interview.study.jvm.oom;

import java.util.Random;

/**
 * @author: WSC
 * @Create 2020/9/10 23:35
 *
 * 1、-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC         （DefNew + Tenured）
 *
 *       -XX:InitialHeapSize=10485760 -XX:MaxHeapSize=10485760 -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseSerialGC
 *       *********************GCDemo hello
 *      [GC (Allocation Failure) [DefNew: 2717K->320K(3072K), 0.0015476 secs] 2717K->965K(9920K), 0.0039385 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 *      [GC (Allocation Failure) [DefNew: 2727K->0K(3072K), 0.0007680 secs][Tenured: 6284K->4948K(6848K), 0.0024003 secs] 6339K->4948K(9920K), [Metaspace: 3262K->3262K(1056768K)], 0.0031921 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 *      [Full GC (Allocation Failure) [Tenured: 4948K->4794K(6848K), 0.0027989 secs] 4948K->4794K(9920K), [Metaspace: 3262K->3262K(1056768K)], 0.0028211 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 *      Heap
 *      def new generation   total 3072K, used 109K [0x00000000ff600000, 0x00000000ff950000, 0x00000000ff950000)
 *          eden space 2752K,   3% used [0x00000000ff600000, 0x00000000ff61b480, 0x00000000ff8b0000)
 *          from space 320K,   0% used [0x00000000ff8b0000, 0x00000000ff8b0000, 0x00000000ff900000)
 *          to   space 320K,   0% used [0x00000000ff900000, 0x00000000ff900000, 0x00000000ff950000)
 *      tenured generation   total 6848K, used 4794K [0x00000000ff950000, 0x0000000100000000, 0x0000000100000000)
 *          the space 6848K,  70% used [0x00000000ff950000, 0x00000000ffdfe880, 0x00000000ffdfea00, 0x0000000100000000)
 *      Metaspace       used 3294K, capacity 4496K, committed 4864K, reserved 1056768K
 *          class space    used 354K, capacity 388K, committed 512K, reserved 1048576K
 *
 */
public class GCDemo {
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
