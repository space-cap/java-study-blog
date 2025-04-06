package ch06;

public class Exercise6_18 {
    int iv = 10;
    static int cv = 20;

    int iv2 = cv;
    //static int cv2 = iv; // static 초기화 블록에서 iv를 사용할 수 없다.

    static void staticMethod() {
        System.out.println(cv);
        //System.out.println(iv); // static 메서드에서 인스턴스 변수를 사용할 수 없다.
    }

    void instanceMethod() {
        System.out.println(cv);
        System.out.println(iv); // 인스턴스 메서드에서 인스턴스 변수를 사용할 수 있다.
    }

    static void staticMethod2() {
        staticMethod();
        //instanceMethod(); // static 메서드에서 인스턴스 메서드를 사용할 수 있다.
    }

    void instanceMethod2() {
        staticMethod();
        instanceMethod(); // 인스턴스 메서드에서 static 메서드를 사용할 수 있다.
    }
}
