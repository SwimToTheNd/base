package com.swimtothend.base.demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解测试类
 * create by BloodFly at 2019/3/27
 */
@MyAnnotation(color = "red", value = "天乾地坤", arrAttr = {8, 9, 10}, lamp = EnumTrafficLamp.YELLOW, annotationAttr = @MetaAnnotation("OtherMeta"))
public class AnnotationDemo {

    public static void main(String[] args) {
        if (AnnotationDemo.class.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation annotation = AnnotationDemo.class.getAnnotation(MyAnnotation.class);
            System.out.println(annotation.color());
            System.out.println(annotation.value());
            System.out.println(annotation.arrAttr().length);
            System.out.println(annotation.annotationAttr().value());
            System.out.println(annotation.lamp());
        }
    }
}

enum EnumTrafficLamp {
    RED,
    YELLOW,
    GREEN;
}

/**
 * 元注解
 */
@interface MetaAnnotation {
    String value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@interface MyAnnotation {

    // 为属性指定缺省值
    String color() default "blue";

    String value();

    // 数组属性
    int[] arrAttr() default {1, 2, 3};

    // 枚举类属性
    EnumTrafficLamp lamp() default EnumTrafficLamp.RED;

    // 注解类属性，指定缺省值
    MetaAnnotation annotationAttr() default @MetaAnnotation("meta");
}