package ch07;

class Outer0505 {
    private String outerField = "외부 클래스 필드";

    // 1. 인스턴스 내부 클래스
    class InstanceInner {
        void print() {
            System.out.println(outerField); // 외부 클래스 멤버 접근 가능
        }
    }

    // 2. 정적 내부 클래스
    static class StaticInner {
        void print() {
            // System.out.println(outerField); // 불가: static 내부 클래스는 외부 인스턴스 멤버 접근 불가
            System.out.println("정적 내부 클래스");
        }
    }

    void method() {
        // 3. 지역 내부 클래스
        class LocalInner {
            void print() {
                System.out.println("지역 내부 클래스");
            }
        }
        LocalInner local = new LocalInner();
        local.print();
    }
}

public class InnerClassEx1 {
    public static void main(String[] args) {
        Outer0505 outer = new Outer0505();

        // 인스턴스 내부 클래스 사용
        Outer0505.InstanceInner instanceInner = outer.new InstanceInner();
        instanceInner.print();

        // 정적 내부 클래스 사용
        Outer0505.StaticInner staticInner = new Outer0505.StaticInner();
        staticInner.print();

        // 지역 내부 클래스 사용
        outer.method();

        // 익명 내부 클래스 예시
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("익명 내부 클래스");
            }
        };
        r.run();
    }

}
