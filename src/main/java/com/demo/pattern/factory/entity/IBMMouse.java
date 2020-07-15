package com.demo.pattern.factory.entity;

public class IBMMouse implements Mouse {

    @Override
    public void sayHi() {
        System.out.println("IBM鼠标");
    }
}
