package com.demo.reflect;

import java.lang.reflect.Field;

public class FieldCollector {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        ReflectTarget reflectTarget = new ReflectTarget();
        Class<?> clazz = reflectTarget.getClass();

        //获取所有公有字段
        Field[] fields = clazz.getFields();
        for(Field field:fields){
            System.out.println(field);
        }

        System.out.println("---------------------");
        //获取所有字段
        Field[] fields1 = clazz.getDeclaredFields();
        for(Field field:fields1){
            System.out.println(field);
        }

        System.out.println("---------------------");
        //获取单个公有字段
        Field field = clazz.getField("str");
        System.out.println(field);
//        clazz.getConstructor(.)0000000000
        System.out.println("---------------------");
        //获取单个字段
        Field field1 = clazz.getDeclaredField("targetInfo");
        field1.setAccessible(true);
        field1.set(reflectTarget, "reflectTarget");
        System.out.println(reflectTarget.toString());
    }
}
