package com.demo.pattern.factory.abstractf;

public class AbstractFactoryDemo {

    public static void main(String[] args) {
        ComputerFactory hp = new HpComputerFactory();
        hp.createKeyboard().sayHello();
        hp.createMouse().sayHi();
    }
}
