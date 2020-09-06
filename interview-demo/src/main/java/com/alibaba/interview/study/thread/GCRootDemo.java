package com.alibaba.interview.study.thread;

/**
 * 如何判断一个对象是否可以被回收？
 *      1、根节点做可达性分析
 *      2、那些可以做GC Root对象
 *          Java 可以做GC Roots的对象
 *              1、虚拟机栈（栈帧中的本地变量表）中引用的对象
 *              2、方法区中的静态属性引用的对象
 *              3、方法区中常量引用的对象
 *              4、本地方法栈中JNI（即一般说的Native方法）中引用的对象
 */
public class GCRootDemo {

    private byte[] byteArray = new byte[100 * 1024 * 1024];
    //private static GCRootDemo2 t2;    //对应2 可做GC Root对象
    //private static final GCRootDemo3 t3 = new GCRootDemo3(8); //对应3 可做GC Root对象
    public static void m1(){
        GCRootDemo t1 = new GCRootDemo(); //对应1 可做GC Root对象
        System.gc();
        System.out.println("第一次GC完成");
    }

    public static void main(String[] args) {
        m1();
    }

}
