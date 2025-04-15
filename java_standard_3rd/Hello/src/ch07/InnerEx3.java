package ch07;

public class InnerEx3 {
    private int outerIv = 0;
    static int outerCv = 0;

    class InstanceInner {
        int iv = outerIv;
        int iv2 = outerCv;
    }

    static class StaticInner {
        // static int iv = outerIv; // 에러! static은 외부 클래스의 인스턴스 멤버에 접근할 수 없다.
        static int cv = outerCv;
    }

    void myMethod(int a) {
        int lv = a; // 지역변수
        final int LV = 0; // 상수
        int age = 0; // 지역변수

        class LocalInner {
            int liv = outerIv;
            int liv2 = outerCv;
            int lv2 = lv;
            int lv3 = LV;
        }
        //lv = age;
    }

    public static void main(String[] args) {
        InnerEx3 outer = new InnerEx3();
        outer.myMethod(10);
    }
}
