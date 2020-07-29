package com.demo.pattern.singleton;

public class StavingSingleton {

    private static final StavingSingleton stavingSingleton = new StavingSingleton();
    private StavingSingleton(){}
    public static StavingSingleton getInstance(){
        return stavingSingleton;
    }
}
