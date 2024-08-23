package com.lyl.cloud.designpatterns.singleton;

/**
 * 双重检查
 */
public class DoublecheckSingletonContext {
    public static void main(String[] args) {
        DoublecheckSingleton doublecheckSingleton = DoublecheckSingleton.getInstance();
        DoublecheckSingleton doublecheckSingleton1 = DoublecheckSingleton.getInstance();
        System.out.println(doublecheckSingleton == doublecheckSingleton1);

    }
}

class DoublecheckSingleton {
    // 私有化构造方法
    private DoublecheckSingleton() {
    }
    private static volatile DoublecheckSingleton doublecheckSingleton;

    public static DoublecheckSingleton getInstance() {
        // 双重检查
        // 第一次检查
        // 线程A执行到第一次检查的时候，线程B进入同步代码块
        // 线程B执行到第一次检查的时候，发现doublecheckSingleton不为null，
        // 线程B执行完同步代码块，线程A继续往下执行，
        // 线程A执行完同步代码块，
        // 线程A和线程B都执行完，
        // 此时doublecheckSingleton已经不为null了，
        // 线程A和线程B都执行完
        if (doublecheckSingleton == null) {
            synchronized (DoublecheckSingleton.class) {
                if (doublecheckSingleton == null) {
                    doublecheckSingleton = new DoublecheckSingleton();
                }
            }
        }
        return doublecheckSingleton;
    }
}
