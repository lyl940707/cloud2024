package com.lyl.cloud.designpatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理
 * 1.JDK动态代理
 */
public class JdkProxyContext {
    public static void main(String[] args) {
        Dog dog = new Dog();
        JdkProxy jdkProxy = new JdkProxy(dog);
        Animal animal = (Animal) java.lang.reflect.Proxy.newProxyInstance(dog.getClass().getClassLoader(), dog.getClass().getInterfaces(), jdkProxy);
        animal.eat();
    }
}

interface Animal {
    void eat();
}

class Dog implements Animal {
    @Override
    public void eat() {
        System.out.println("dog eat");
    }
}

class JdkProxy implements InvocationHandler {
    private Object target;

    public JdkProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName());
        return method.invoke(target, args);
    }
}