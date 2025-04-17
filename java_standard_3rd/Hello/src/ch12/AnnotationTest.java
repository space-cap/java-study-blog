package ch12;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;

@Target({FIELD, TYPE, TYPE_USE, METHOD})
@interface MyAnnotation {}

@MyAnnotation
class MyClass {
    @MyAnnotation
    int i;

    @MyAnnotation
    MyClass() {}

    @MyAnnotation
    void myMethod(@MyAnnotation int i) {}

    @MyAnnotation
    int myMethod2(@MyAnnotation int i) {
        return 0;
    }
}


public class AnnotationTest {
    public static void main(String[] args) {
        System.out.println("Hello, World!");


    }
}
