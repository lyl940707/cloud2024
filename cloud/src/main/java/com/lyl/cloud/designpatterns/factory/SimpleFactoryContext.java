package com.lyl.cloud.designpatterns.factory;

/**
 * 简单工厂模式
 * @author lyl
 * 简单工厂模式：
 * 1. 简单工厂模式：定义一个创建对象的类，由这个类来决定要实例化的具体类是哪一个
 * 2. 简单工厂模式：由一个工厂类决定创建出哪一种产品类
 * 3. 简单工厂模式：将对象的创建与使用分离
 */
public class SimpleFactoryContext {
    public static void main(String[] args) {
        SimpleFactory factory = new SimpleFactory();
        chair chair = factory.createChair();
        table table = factory.createTable();
    }
}

class chair {
    public chair() {
        System.out.println("chair");
    }
}

class table {
    public table() {
        System.out.println("table");
    }
}

class SimpleFactory {
    public chair createChair() {
        return new chair();
    }
    public table createTable() {
        return new table();
    }
}
