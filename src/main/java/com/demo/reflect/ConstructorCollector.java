package com.demo.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstructorCollector {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<ReflectTarget> clazz = ReflectTarget.class;
        Class<?> clazz1 = Class.forName("com.demo.reflect.ReflectTarget");

        //所有公有构造方法
        Constructor<?>[] constructors = clazz.getConstructors();
        for(Constructor<?> constructor : constructors){
            System.out.println(constructor);
        }

        System.out.println("--------------------------");
        //所有构造方法
        Constructor<?>[] constructors1 = clazz1.getDeclaredConstructors();
        for(Constructor<?> constructor : constructors1){
            System.out.println(constructor);
        }

        System.out.println("--------------------------");
        //单个公有构造方法
        Constructor<?> constructor2 = clazz1.getConstructor(String.class, int.class);
        System.out.println(constructor2);

        Constructor<?> constructor3 = clazz1.getDeclaredConstructor(int.class);
        constructor3.setAccessible(true);
        constructor3.newInstance(2);

    }
}
