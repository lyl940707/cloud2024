package com.lyl.cloud.designpatterns.singleton;

/**
 * 饿汉模式：在类加载的时候就创建好一个静态的对象供外部使用，除非销毁JVM
 * 优点：不存在线程安全问题
 * 缺点：类加载的时候就初始化，浪费内存
 * 适用场景：类加载时，就创建好一个对象供外部使用
 */
public class HungurySingletonContext {
    public static void main(String[] args) {
        HungrySingleton instance = HungrySingleton.getInstance();
        HungrySingleton instance2 = HungrySingleton.getInstance();
        System.out.println(instance == instance2);
    }
}

class HungrySingleton {
    private HungrySingleton() {
    }
    private static HungrySingleton instance = new HungrySingleton();

    public static HungrySingleton getInstance() {
        return instance;
    }
}
