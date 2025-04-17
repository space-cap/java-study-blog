package ch07;

class A {
    static void staticMethod() {
        System.out.println("A의 static 메서드");
    }

    private void privateMethod() {
        System.out.println("A의 private 메서드");
    }
}

class B extends A {
    static void staticMethod() {
        System.out.println("B의 static 메서드");
    }

    private void privateMethod() {
        System.out.println("B의 private 메서드");
    }
}


public class DynamicBindingTest {
    public static void main(String[] args) {
        // 1. 다형성
        // 2. 메소드 오버라이딩
        // 3. 동적 바인딩
        // 4. 메소드 오버로딩
        // 5. 메소드 시그니처
        // 6. 메소드 오버로딩과 메소드 오버라이딩의 차이점

        // 다형성 : 하나의 객체가 여러 형태를 가질 수 있는 성질
        // 메소드 오버라이딩 : 부모 클래스의 메소드를 자식 클래스에서 재정의하는 것
        // 동적 바인딩 : 실행 시간에 메소드를 결정하는 것
        // 메소드 오버로딩 : 같은 이름의 메소드를 여러 개 정의하는 것
        // 메소드 시그니처 : 메소드 이름과 매개변수의 타입, 개수, 순서
        // 메소드 오버로딩과 메소드 오버라이딩의 차이점 : 메소드 오버로딩은 컴파일 시간에 결정되고, 메소드 오버라이딩은 실행 시간에 결정된다.

        A b = new B();
        b.staticMethod(); // A의 static 메서드
        // b.privateMethod(); // 컴파일 에러

    }
}
