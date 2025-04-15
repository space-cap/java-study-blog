package ch07;

public class AnonymousClassTest {
    public static void main(String[] args) {
        Hello h = new Hello() {
            @Override
            void say() {
                System.out.println("안녕하세요~");
            }
        };
        h.say();  // 출력: 안녕하세요~
    }
}

class Hello {
    void say() {
        System.out.println("Hello!");
    }
}
