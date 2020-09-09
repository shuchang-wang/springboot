package com.alibaba.interview.study.thread;

/**
 * @author: WSC
 * @Create 2020/9/9 21:24
 * 栈溢出异常：递归调用方法
 * Exception in thread "main" java.lang.StackOverflowError
 * java.lang.Object
 *  java.lang.Throwable
 *      java.lang.Error
 *          java.lang.VirtualMachineError
 *           java.lang.StackOverflowError
 *
 */
public class StackOverflowErrorDemo {
    public static void main(String[] args) {
        stackOverflowError();
    }

    private static void stackOverflowError() {
        stackOverflowError();//Exception in thread "main" java.lang.StackOverflowError
    }
}
