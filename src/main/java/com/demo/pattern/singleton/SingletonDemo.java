package com.demo.pattern.singleton;

import java.util.HashSet;
import java.util.Set;

public class SingletonDemo {

    public static void main(String[] args) {

        for(int i=0; i<100; i++){
            new Thread(()-> System.out.println(CheckSingleton.holder)).start();
        }

    }
}
