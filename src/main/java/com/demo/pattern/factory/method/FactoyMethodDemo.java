package com.demo.pattern.factory.method;

public class FactoyMethodDemo {

    public static void main(String[] args) {
        MouseFactory mf = new HpMouseFactory();
        mf.createMouse().sayHi();
    }
}
