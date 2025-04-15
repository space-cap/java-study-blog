package ch07;

class Outer726 {
    static class Inner {
        int iv = 200;
    }
}

public class Exercise7_26 {
    public static void main(String[] args) {
        Outer726 outer = new Outer726();
        Outer726.Inner inner = new Outer726.Inner();
        System.out.println(inner.iv); // 100
    }
}
