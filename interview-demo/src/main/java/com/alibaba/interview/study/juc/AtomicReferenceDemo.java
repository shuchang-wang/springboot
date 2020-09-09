package com.alibaba.interview.study.juc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: WSC
 * @Create 2020/8/30 14:00
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
class User {
    private String name;
    private int age;
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User zs = new User("zhangsan", 23);
        User ls = new User("lisi", 24);
        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        userAtomicReference.set(zs);
        System.out.println(userAtomicReference.compareAndSet(zs, ls) + "\t" + userAtomicReference.get().toString());
        System.out.println(userAtomicReference.compareAndSet(zs, ls) + "\t" + userAtomicReference.get().toString());
    }
}
