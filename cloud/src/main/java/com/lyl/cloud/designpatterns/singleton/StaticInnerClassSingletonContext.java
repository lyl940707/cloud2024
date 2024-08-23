package com.lyl.cloud.designpatterns.singleton;

/**
 * 静态内部类实现单例模式
 * 优点：实现了延迟加载，线程安全，效率高
 * 缺点：只能使用默认的构造方法
 */
public class StaticInnerClassSingletonContext {
    public static void main(String[] args) {
        StaticInnerClassSingleton instance = StaticInnerClassSingleton.getInstance();
        StaticInnerClassSingleton instance1 = StaticInnerClassSingleton.getInstance();
        System.out.println(instance1 == instance);
    }
}
// 静态内部类实现单例模式
// 优点：实现了延迟加载，线程安全，效率高
// 缺点：只能使用默认的构造方法
// 适用场景：适用于单例模式非常多的情况下
// 适用场景：单例模式非常少的情况下

class StaticInnerClassSingleton {
    // 构造方法私有化
    private StaticInnerClassSingleton() {
        System.out.println("StaticInnerClassSingletonContext 构造方法");
    }
    // 静态内部类
    private static class SingletonHolder {
        private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }
    // 获取实例
    public static StaticInnerClassSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
