package com.demo.pattern.singleton;

import java.util.Random;

public class CheckSingleton {

    public static Holder holder = getHolder();

    private static Holder getHolder(){
        return new Holder();
    }

    private static final class Holder{
        private final int a;
        public Holder(){
            this.a = new Random().nextInt(100);
        }
        public String getA(){
            return String.valueOf(this.a);
        }
    }
}

