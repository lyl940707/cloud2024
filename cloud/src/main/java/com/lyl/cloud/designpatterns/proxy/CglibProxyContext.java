package com.lyl.cloud.designpatterns.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 代理模式：cglib动态代理
 * 1. 代理模式：为其他对象提供一种代理，以控制对这个对象的访问。
 * 2. 代理模式：代理对象，在实现接口的基础上，对方法进行增强。
 */
public class CglibProxyContext {
    public static void main(String[] args) {
        Cat cat = new Cat();
        CatProxy catProxy = new CatProxy(cat);
        // 1. 创建Enhancer对象
        Enhancer enhancer = new Enhancer();
        // 2. 设置父类
        enhancer.setSuperclass(cat.getClass());
        // 3. 设置回调函数
        enhancer.setCallback(catProxy);
        // 4. 创建代理对象
        Cat c = (Cat)enhancer.create();
        // 5. 调用代理对象的方法
        c.eat();
//        Cat proxyCat = (Cat)Enhancer.create(cat.getClass(), null, catProxy);
//        proxyCat.eat();
    }
}

class Cat{
    public void eat(){
        System.out.println("吃鱼");
    }
}
class CatProxy implements MethodInterceptor {
    private Cat cat;

    public CatProxy(Cat cat){
        this.cat = cat;
    }
    public void eat(){
        System.out.println("吃鱼");
    }

    /**
     *
     * @param obj 代理对象
     * @param method 代理对象的方法
     * @param args 代理对象的方法参数
     * @param proxy 代理对象的方法代理
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("吃鱼之前");
        // 调用被代理对象的方法
        return proxy.invokeSuper(obj, args);
    }
}
