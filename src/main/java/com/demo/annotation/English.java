package com.demo.annotation;

@CourseInfoAnnotation(
        courseName = "起的名字",
        courseTag = "提升",
        courseProfile = "简介")
public class English {

    @PersonInfoAnnotation(name = "hh", language = {"java", "c++"})
    private String author;

    @CourseInfoAnnotation(
            courseName = "起的名字",
            courseTag = "提升",
            courseProfile = "简介",
    courseIndex = 404)
    public void getCourseInfo(){

    }
}
