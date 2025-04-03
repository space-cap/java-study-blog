package ch06;

class Data2 {
    int x;
}

public class Ex6_7 {
    public static void main(String[] args) {
        Data2 d = new Data2();

        // 주소값을 10진수로 출력
        System.out.println(System.identityHashCode(d));

        // 주소값을 16진수로 출력
        System.out.println("main() : d = " + Integer.toHexString(System.identityHashCode(d)));

        d.x = 10;
        System.out.println("main() : x = " + d.x);

        change(d);
        System.out.println("After change() : x = " + d.x);
        System.out.println("main() : x = " + d.x);
    }

    static void change(Data2 d) {
        System.out.println("change() : d = " + Integer.toHexString(System.identityHashCode(d)));

        d.x = 1000;
        System.out.println("change() : x = " + d.x);
    }


}
