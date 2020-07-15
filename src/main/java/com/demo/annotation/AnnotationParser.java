package com.demo.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class AnnotationParser {

    //解析类的注解
    public static void parseTypeAnnotation() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.demo.annotation.JokerCourse");

        //获取class的注解，而非成员或方法的注解
        Annotation[] annotations = clazz.getAnnotations();
        CourseInfoAnnotation annotation2 = clazz.getAnnotation(CourseInfoAnnotation.class);
        System.out.println(annotation2);
        for (Annotation annotation : annotations) {
            CourseInfoAnnotation annotation1 = (CourseInfoAnnotation) annotation;
            System.out.println("课程名：" + annotation1.courseName());
            System.out.println("课程标签：" + annotation1.courseTag());
            System.out.println("课程简介：" + annotation1.courseProfile());
            System.out.println("课程序号：" + annotation1.courseIndex());
        }
    }

    //解析成员变量的注解
    public static void parseFieldAnnotation() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.demo.annotation.JokerCourse");

        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            boolean hasAnnotation = declaredField.isAnnotationPresent(PersonInfoAnnotation.class);
            if(hasAnnotation){
                PersonInfoAnnotation annotation = declaredField.getAnnotation(PersonInfoAnnotation.class);
                System.out.println(annotation);
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
//        parseTypeAnnotation();
        parseFieldAnnotation();
    }


}
