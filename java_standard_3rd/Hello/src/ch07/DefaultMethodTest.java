package ch07;

public class DefaultMethodTest {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
        System.out.println("=====================");

        Child0505 child = new Child0505();
        child.method1();
        child.method2();
        MyInterface.staticmethod();
        MyInterface2.staticmethod();
    }
}

class Child0505 extends Parent0505 implements MyInterface2, MyInterface {
    public void method1() {
        System.out.println("Child method1");
    }

    @Override
    public void method2() {
        // 원하는 인터페이스의 default 메서드를 호출하거나, 직접 구현
        MyInterface.super.method2();
        MyInterface2.super.method2();
        // 또는 완전히 새로운 구현
    }
}

class Parent0505 {
    public void method21() {
        System.out.println("Parent method2");
    }
}

interface MyInterface {
    default void method1() {
        System.out.println("MyInterface method1");
    }

    default void method2() {
        System.out.println("MyInterface method2");
    }

    static void staticmethod() {
        System.out.println("MyInterface staticmethod");
    }
}

interface MyInterface2 {
    default void method1() {
        System.out.println("MyInterface2 method1");
    }

    default void method2() {
        System.out.println("MyInterface2 method2");
    }

    static void staticmethod() {
        System.out.println("MyInterface2 staticmethod");
    }
}

