package ch07;

class Outer729 {
    int age = 10;
    static int month = 20;

    void method() {
        System.out.println("Outer class method");

        int age = 30;
        final int localAge = 50; // final local variable

        class LocalInner {
            int innerAge = 40;
            void localMethod() {
                System.out.println("Local inner class method");
                System.out.println("age : " + age);
                System.out.println("Outer.this.age : " + Outer729.this.age); // 10
                System.out.println("Outer.month : " + Outer729.month); // 20
                System.out.println("localAge : " + localAge); // 50
                System.out.println("localAge : " + age);
                System.out.println("Outer.this.month : " + Outer729.month); // 20
                System.out.println("Outer.this.age : " + Outer729.this.age); // 10

            }
        }

        LocalInner localInner = new LocalInner();
        localInner.localMethod(); // Local inner class method




    }
}



public class Exercise7_29 {
    public static void main(String[] args) {
        Outer729 outer = new Outer729();
        outer.method(); // Outer class method
    }
}
