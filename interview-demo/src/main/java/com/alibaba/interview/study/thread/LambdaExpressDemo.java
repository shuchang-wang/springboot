package com.alibaba.interview.study.thread;

/**
 * @author: WSC
 * @Create 2020/9/13 20:33
 *
 * lambda表达式
 *      前提：接口中只有一个方法
 *  2.Lambda Express
 *     2.1 口诀：
 *              拷贝小括号，写死右箭头，落地大括号
 *     2.2 @FunctionalInterface
 *     2.3 default【java8之前只允许有实现的声明不允许有方法的实现，java8之后允许有方法的实现且必须使用default关键字；允许多个default修饰的方法】
 *     2.4 静态方法实现【允许多个静态方法】
 */
@FunctionalInterface
interface Foo {
    public abstract int add(int x,int y);//方法的声明

    default int div(int x, int y){//方法的实现
        System.out.println("div!");
        return x/y;
    }

    default int div2(int x, int y){//方法的实现
        System.out.println("div2!");
        return x/y;
    }

    static int mv(int x, int y){//方法的实现
        System.out.println("mv!");
        return x * y;
    }

    static int mv2(int x, int y){//方法的实现
        System.out.println("mv!");
        return x * y;
    }
}
public class LambdaExpressDemo {
    public static void main(String[] args) {
//        Foo foo = (int x,int y)->{
//            System.out.println("Hello!");
//            return x+y;
//        };

        Foo foo = (x,y)->{
            System.out.println("add!");
            return x+y;
        };
        System.out.println(foo.add(1, 0));
        System.out.println(foo.div(4, 2));
        System.out.println(foo.div2(6, 2));
        System.out.println(Foo.mv(2, 3));
        System.out.println(Foo.mv2(3, 3));
    }
}
