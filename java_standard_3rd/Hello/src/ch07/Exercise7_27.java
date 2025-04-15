package ch07;

class Outer727 {
    int value=10;
    class Inner {
        int value = 20;
        void method() {
            int value = 30;
            System.out.println("value : " + value); // 30
            System.out.println("this.value : " + Inner.this.value); // 20
            System.out.println("Outer.this.value : " + Outer727.this.value); // 10


        }
    }
}

public class Exercise7_27 {
    public static void main(String[] args) {
        Outer727 outer = new Outer727();
        Outer727.Inner inner = outer.new Inner();
        inner.method(); // 30, 20, 10
    }
}
