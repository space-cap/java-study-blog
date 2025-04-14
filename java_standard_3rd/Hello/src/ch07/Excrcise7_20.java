package ch07;

public class Excrcise7_20 {
    public static void main(String[] args) {
        System.out.println("hello");

        Parent720 p = new Child720();
        Child720 c = new Child720();

        System.out.println("p.x: " + p.x);
        p.method();

        System.out.println("c.x: " + c.x);
        c.method();
    }
}

class Parent720 {
    int x = 100;

    void method() {
        System.out.println("Parent720 method");
    }
}

class Child720 extends Parent720 {
    int x = 200;

    void method() {
        System.out.println("Child720 method");
    }
}
