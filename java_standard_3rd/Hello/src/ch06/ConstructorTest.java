package ch06;

class Parent250409 {
    public Parent250409() {
        System.out.println("부모 생성자 호출됨");
    }
}

class Child250409 extends Parent250409 {
    public Child250409(String msg) {
        System.out.println("자식 생성자: " + msg);
    }
}

public class ConstructorTest {
    public static void main(String[] args) {
        System.out.println("main() start");

        Child250409 c = new Child250409("안녕하세요");
    }
}
