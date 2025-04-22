package ch12;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

// @MyAnnotation 이라는 이름의 애너테이션 정의
@Retention(RetentionPolicy.RUNTIME) // 런타임까지 유지
@Target(ElementType.METHOD)         // 메서드에만 적용 가능
@interface MyAnnotation0421 {
    String value();                 // value라는 속성(필수)
    int number() default 0;         // number라는 속성(기본값 0)
    int power() default 1;          // power라는 속성(기본값 1)
}

class AnnotationExample0421 {

    @MyAnnotation0421(value = "테스트 메서드", number = 100, power = 2) // 모든 속성 지정
    public void testMethod() {
        System.out.println("testMethod 실행");
    }

    @MyAnnotation0421(value = "간단한 메서드", power = 15) // value만 지정(기본값 사용)
    public void simpleMethod() {
        System.out.println("simpleMethod 실행");
    }
}


public class AnnotationTest2 {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        AnnotationExample0421 obj = new AnnotationExample0421();
        Method[] methods = obj.getClass().getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(MyAnnotation0421.class)) {
                MyAnnotation0421 anno = method.getAnnotation(MyAnnotation0421.class);
                System.out.println(
                        method.getName() + " -> value: " + anno.value() + ", power: " + anno.power()
                );
            }
        }
    }
}
