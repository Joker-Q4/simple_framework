package com.demo.pattern.singleton;

/**
 * 双重检查锁机制的懒汉模式
 */
public class LazyDoubleCheckSingleton {

    private volatile static LazyDoubleCheckSingleton instance;

    private LazyDoubleCheckSingleton(){}

    public static LazyDoubleCheckSingleton getInstance(){


        //1.第一次检测
        if(instance == null){
            //同步
            synchronized (LazyDoubleCheckSingleton.class){
                if(instance == null){
                    //1.分配对象内存空间
                    //2.初始化对象
                    //3.设置instance指向刚分配的内存地址，此时instance!=null
                    instance = new LazyDoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
