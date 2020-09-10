package com.alibaba.interview.study.jvm.oom;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: WSC
 * @Create 2020/9/10 21:21
 * JVM参数
 *      -XX:MetaspaceSize=12m -XX:MaxMetaspaceSize=12m
 *  错误信息：
 *      Caused by: java.lang.OutOfMemoryError: Metaspace
 * Java 8及以后的版本使用Metaspace来代替永久代。
 * Metaspace是方法区在HostSpot中的实现，它与持久代最大的区别在于：Metaspace并不在虚拟机内存中而是使用本地内存。
 * 也即在java8中，class metadata(the virtual machines internal presentation of java class)，被存储在叫做Metaspace的native memory。
 * <p>
 * 永久代（java8后被元空间Metaspace取代了）存放以下信息：
 *      1.虚拟机加载了的类信息
 *      2.常量池
 *      3.静态变量
 *      4.即时编译后的代码
 * <p>
 * 模拟Metaspace空间溢出，我们不断生成类往元空间灌，类占据的空间总是会超过Metaspace指定的空间大小的。
 */
public class MetaspaceOOMDemo {
    static class OOMTest {
    }

    public static void main(String[] args) {
        int i = 0;
        try {
            while (true) {
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invoke(o, args);
                    }
                });
                enhancer.create();
            }
        } catch (Throwable e) {
            System.out.println("*************************多少次后发生了异常："+i);
            e.printStackTrace();
        }
    }
}
