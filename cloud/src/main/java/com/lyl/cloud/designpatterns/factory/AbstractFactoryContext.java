package com.lyl.cloud.designpatterns.factory;

/**
 * 抽象工厂模式
 * 定义一个创建产品对象的工厂接口，将产品对象的实例化延迟到具体子工厂类
 */
public class AbstractFactoryContext {
    public static void main(String[] args) {
        Factory factory = new ConcreteFactory1();
        ProductA productA = factory.createProductA();
        productA.method();
        ProductB productB = factory.createProductB();
        productB.method();

        Factory factory2 = new ConcreteFactory2();
        ProductA productA2 = factory2.createProductA();
        productA2.method();
        ProductB productB2 = factory2.createProductB();
        productB2.method();
    }
}

interface ProductA {
    void method();
}
interface ProductB {
    void method();
}

class ConcreteProductA1 implements ProductA {
    @Override
    public void method() {
        System.out.println("具体产品A1的实现方法");
    }
}
class ConcreteProductA2 implements ProductA {
    @Override
    public void method() {
        System.out.println("具体产品A2的实现方法");
    }
}

class ConcreteProductB1 implements ProductB {
    @Override
    public void method() {
        System.out.println("具体产品B1的实现方法");
    }
}

class ConcreteProductB2 implements ProductB {
    @Override
    public void method() {
        System.out.println("具体产品B2的实现方法");
    }
}

interface Factory {
    ProductA createProductA();
    ProductB createProductB();
}

class ConcreteFactory1 implements Factory {
    @Override
    public ProductA createProductA() {
        return new ConcreteProductA1();
    }
    @Override
    public ProductB createProductB() {
        return new ConcreteProductB1();
    }
}

class ConcreteFactory2 implements Factory {
    @Override
    public ProductA createProductA() {
        return new ConcreteProductA2();
    }
    @Override
    public ProductB createProductB() {
        return new ConcreteProductB2();
    }
}