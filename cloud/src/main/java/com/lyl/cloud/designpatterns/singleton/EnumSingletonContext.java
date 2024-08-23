package com.lyl.cloud.designpatterns.singleton;

/**
 * @author lyl
 * 枚举单例模式
 * 优点：
 * 1. 线程安全
 * 2. 防止反射和反序列化攻击
 * 缺点：
 * 1. 不能延迟加载
 * 2. 反射和反序列化不能破解
 * 3. 枚举类不能被继承
 * 4. 枚举类不能有子类
 */
public class EnumSingletonContext {
    public static void main(String[] args) {
        EnumSingleton instance = EnumSingleton.INSTANCE;
        EnumSingleton instance2 = EnumSingleton.INSTANCE;
        System.out.println(instance == instance2);
    }
}

enum EnumSingleton {
    INSTANCE;
    public void method() {
        System.out.println("EnumSingleton");
    }
}
