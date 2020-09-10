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
 *  2、-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC         （ParNew + Tenured）
 *      备注说明：Java HotSpot(TM) 64-Bit Server VM warning: Using the ParNew young collector with the Serial old collector is deprecated and will likely be removed in a future release
 *
 *      -XX:InitialHeapSize=10485760 -XX:MaxHeapSize=10485760 -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseParNewGC
 *      *********************GCDemo hello
 *      [GC (Allocation Failure) [ParNew: 2749K->320K(3072K), 0.0009144 secs] 2749K->989K(9920K), 0.0009751 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 *      [GC (Allocation Failure) [ParNew: 1340K->1340K(3072K), 0.0000317 secs][Tenured: 5669K->3661K(6848K), 0.0038112 secs] 7010K->3661K(9920K), [Metaspace: 3253K->3253K(1056768K)], 0.0038918 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 *      [Full GC (Allocation Failure) [Tenured: 4822K->4802K(6848K), 0.0027007 secs] 4822K->4802K(9920K), [Metaspace: 3253K->3253K(1056768K)], 0.0027203 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 *      Heap
 *          par new generation   total 3072K, used 80K [0x00000000ff600000, 0x00000000ff950000, 0x00000000ff950000)
 *              eden space 2752K,   2% used [0x00000000ff600000, 0x00000000ff614220, 0x00000000ff8b0000)
 *              from space 320K,   0% used [0x00000000ff900000, 0x00000000ff900000, 0x00000000ff950000)
 *              to   space 320K,   0% used [0x00000000ff8b0000, 0x00000000ff8b0000, 0x00000000ff900000)
 *          tenured generation   total 6848K, used 4802K [0x00000000ff950000, 0x0000000100000000, 0x0000000100000000)
 *              the space 6848K,  70% used [0x00000000ff950000, 0x00000000ffe00b40, 0x00000000ffe00c00, 0x0000000100000000)
 *          Metaspace       used 3284K, capacity 4496K, committed 4864K, reserved 1056768K
 *              class space    used 354K, capacity 388K, committed 512K, reserved 1048576K
 *
 *  3、-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC         （PSYoungGen + ParOldGen）
 *
 *      -XX:InitialHeapSize=10485760 -XX:MaxHeapSize=10485760 -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseParallelGC
 *      [GC (Allocation Failure) [PSYoungGen: 2048K->504K(2560K)] 2048K->948K(9728K), 0.0007253 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 *      *********************GCDemo hello
 *      [GC (Allocation Failure) [PSYoungGen: 2482K->504K(2560K)] 2926K->1164K(9728K), 0.0007802 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 *      [Full GC (Allocation Failure) [PSYoungGen: 0K->0K(2048K)] [ParOldGen: 4837K->4818K(7168K)] 4837K->4818K(9216K), [Metaspace: 3262K->3262K(1056768K)], 0.0064198 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
 *      Heap
 *      PSYoungGen      total 2048K, used 57K [0x00000000ffd00000, 0x0000000100000000, 0x0000000100000000)
 *          eden space 1024K, 5% used [0x00000000ffd00000,0x00000000ffd0e698,0x00000000ffe00000)
 *          from space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
 *          to   space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
 *      ParOldGen       total 7168K, used 4818K [0x00000000ff600000, 0x00000000ffd00000, 0x00000000ffd00000)
 *          object space 7168K, 67% used [0x00000000ff600000,0x00000000ffab48a0,0x00000000ffd00000)
 *      Metaspace       used 3293K, capacity 4496K, committed 4864K, reserved 1056768K
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
