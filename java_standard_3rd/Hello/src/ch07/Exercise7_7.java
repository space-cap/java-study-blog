package ch07;

class Parent {
    int x = 100;

    Parent() {
        this(200);
        System.out.println("Parent() constructor called");
    }

    Parent(int x) {
        this.x = x;
        System.out.println("Parent(int x) constructor called");
    }

    int getX() {
        return x;
    }
}

class Child extends Parent {
    int x = 300;

    Child() {
        this(1000);
        System.out.println("Child() constructor called");
    }

    Child(int x) {
        this.x = x;
        System.out.println("Child(int x) constructor called");
    }
}

public class Exercise7_7 {
    public static void main(String[] args) {
        Child c = new Child();
        System.out.println("x=" + c.getX());
    }
}
