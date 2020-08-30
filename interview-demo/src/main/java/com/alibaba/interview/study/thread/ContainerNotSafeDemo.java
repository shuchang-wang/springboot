package com.alibaba.interview.study.thread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author: WSC
 * @Create 2020/8/30 16:32
 * 集合类不安全的问题
 * ArrayList
 */
public class ContainerNotSafeDemo {

    public static void main(String[] args) {
        //List
        listNotSafe();

        //Set
        setNotSafe();

        //Map
        mapNotSafe();

    }

    /**
     * Map集合安全性解决方案：
     *  1、new Hashtable<>();
     *  2、Collections.synchronizedMap(new HashMap<>());
     *  3、new ConcurrentHashMap<>();
     *
     */
    public static void mapNotSafe() {
        Map<String,String> map = new ConcurrentHashMap<>();//Collections.synchronizedMap(new HashMap<>());//new Hashtable<>();//new HashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                //java.util.ConcurrentModificationException
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },"Thread-"+i).start();
        }
    }

    /**
     *   set集合的安全性解决方案：
     *      1、Collections.synchronizedSet(new HashSet<>());
     *      2、new CopyOnWriteArraySet<>();  //底层是使用了CopyOnWriteArrayList
     */
    public static void setNotSafe() {
        Set<String> set = new CopyOnWriteArraySet<>();//Collections.synchronizedSet(new HashSet<>());//new HashSet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                //java.util.ConcurrentModificationException
              set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },"Thread-"+i).start();
        }
    }

    /**
     *  List集合安全性解决方案：
     *      1、使用线程安全的集合Vector，new Vector<>();
     *      2、使用集合辅助类Collections;Collections.synchronizedList(new ArrayList<>());
     *      3、new CopyOnWriteArrayList();
     */
    public static void listNotSafe() {
        //ArrayList初始化容器大小size=10；扩容按1.5倍进行扩容；底层是Object类型的数组
        new ArrayList<Integer>().add(1);

        List<String> list = Arrays.asList("a", "b", "c");
        list.forEach(System.out::println);

        List<String> list1 = new CopyOnWriteArrayList();//Collections.synchronizedList(new ArrayList<>());//new Vector<>();// new ArrayList<String>();
        //Vector:jdk1.0 加synchronize修饰；线程安全，并发性下降
        //ArrayList:jdk1.2，线程不安全，并发性高
        //Collections:集合辅助工具栏；Collections.synchronizedXxx(Xxx);将集合转换为线程安全
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list1.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(Thread.currentThread().getName()+"\t"+list1);
            }, "Thread-" + String.valueOf(i)).start();
        }
        //java.util.ConcurrentModificationException并发修改异常
        /**
         * 不要只是会用，会用只不过是API调用工程师
         * 需要掌握底层原理？
         *
         * 1、故障现象
         *      java.util.ConcurrentModificationException
         * 2、导致原因
         *      并发争抢修改导致，参考我们的花名册签名情况：
         *          一个人正在写入，另一个同学过来抢夺，导致数据不一致异常。从而导致并发修改异常。
         * 3、解决方案
         *      3.1 使用线程安全的集合Vector，new Vector<>();
         *      3.2 使用集合辅助类Collections;Collections.synchronizedList(new ArrayList<>());
         *      3.3 new CopyOnWriteArrayList();
         * 4、优化建议（同样的错误不再犯第2次）
         *
         *  笔记：写时复制
         * copyOnWrite 容器即写时复制的容器。往一个容器添加元素的时候,不直接往当前容器object[]添加,
         * 而是先将当前容器object[]进行copy，复制出一个新的object[] newElements，
         * 然后向新容器object[] newElements 里面添加元素，添加元素之后,
         * 再将原容器的引用指向新的容器 setArray(newElements);
         * 这样的好处是可以对copyOnWrite容器进行并发的读,而不需要加锁，
         * 因为当前容器不会添加任何元素.所以copyOnwrite容器也是一种读写分离的思想,读和写不同的容器。
         *    public boolean add(E e) {
         *         final ReentrantLock lock = this.lock;
         *         lock.lock();
         *         try {
         *             Object[] elements = getArray();
         *             int len = elements.length;
         *             Object[] newElements = Arrays.copyOf(elements, len + 1);
         *             newElements[len] = e;
         *             setArray(newElements);
         *             return true;
         *         } finally {
         *             lock.unlock();
         *        }
         *   }
         */
    }

}
