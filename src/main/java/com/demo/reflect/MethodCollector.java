package com.demo.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodCollector {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Class.forName("com.demo.reflect.ReflectTarget");
        ReflectTarget reflectTarget = (ReflectTarget) clazz.getConstructor().newInstance();

        //获取公有方法
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        System.out.println("---------------------------------");
        //获取私有方法
        Method[] method1 = clazz.getDeclaredMethods();
        for (Method method : method1) {
            System.out.println(method);
        }

        System.out.println("---------------------------------");
        //获取单个公有方法
        Method method = clazz.getMethod("show2");
        method.invoke(reflectTarget);

        System.out.println("---------------------------------");
        //获取单个私有方法
        Method method2 = clazz.getDeclaredMethod("show4", int.class);
        method2.setAccessible(true);
        String a = (String) method2.invoke(reflectTarget, 1);
        System.out.println("a:" + a);
    }
}
