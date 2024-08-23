package com.lyl.cloud.designpatterns.factory;

/**
 * @author liuyuan
 * @date 2019/7/31
 * @description
 * 工厂方法模式
 * 定义一个用于创建对象的接口，让子类决定实例化哪一个类。
 * 工厂方法使一个类的实例化延迟到其子类。
 * 工厂方法模式又称为虚拟构造器模式，它是一种类创建型模式。
 * 优点：
 * 1、良好的封装性，代码结构清晰。
 * 2、可扩展性，增加子类很方便。
 * 3、屏蔽产品类，调用者只关心产品的接口。
 * 4、可屏蔽产品的具体实现。
 * 缺点：
 * 1、每次增加一个产品时，都需要增加一个具体类和对象实现工厂，使得系统中类的个数成倍增加，在一定程度上增加了系统的复杂度。
 * 2、增加了系统的复杂度，需要定义一个工厂接口，如果系统需要添加一个产品，则所有的工厂类都需要进行修改。
 * 3、增加了系统的开销，每次如果增加一个产品，都需要增加一个具体类和对象实现工厂，使得系统中类的个数成倍增加。
 * 4、违背了依赖倒置原则，工厂类依赖于产品类。
 * 适用场景：
 * 1、日志记录器：记录可能记录到本地、数据库或网络上的信息。
 * 2、数据库访问：当用户不知道最后系统采用哪一类数据库，以及数据库可能有变化时。
 * 3、设计一个连接池类：需要选择合适的数据源，连接池负责分配、管理、释放数据库连接。
 * 4、设计一个简单框架：一个软件框架需要实现一个核心功能，而且这个核心功能是可选的。
 * 5、需要依赖产品对象创建一些其它的对象：在类中通过一个工厂方法直接调用工厂来创建产品，客户端代码只需关心产品的接口。
 * 6、需要一个产品对象的工厂：提供一个创建对象的类，该类在程序启动时实例化一个对象给客户端调用，且该源对象只有一个。
 * 7、需要生成不同参数的工厂：提供一个创建对象的类，但将选择和配置委托给子类。
 * 8、需要一个产品对象的多个实例：当一个类不能直接实例化，只能实例化它的子类时。
 * 9、需要一个产品对象的实例，且该产品的创建过程比较复杂：当一个类不能直接实例化，只能实例化它的子类时。
 */
public class FactoryMethodContext {
    public static void main(String[] args) {
        FactoryMethod factoryMethod = new FactoryMethod1();
        book book = factoryMethod.create();
        System.out.println(book);
        FactoryMethod factoryMethod2 = new FactoryMethod2();
        book book2 = factoryMethod2.create();
        System.out.println(book2);
    }
}

interface book{
    void book();
}
class book1 implements book{
    @Override
    public void book() {
        System.out.println("book1");
    }
}

class book2 implements book{
    @Override
    public void book() {
        System.out.println("book2");
    }
}

interface FactoryMethod {
    book create();
}
class FactoryMethod1 implements FactoryMethod{

    @Override
    public book create() {
        return new book1();
    }
}

class FactoryMethod2 implements FactoryMethod{

    @Override
    public book create() {
        return new book2();
    }
}