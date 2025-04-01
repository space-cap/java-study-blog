package ch05;

public class ex01 {
    static public void main(String[] args) {
        // int i = Integer.MAX_VALUE;
        // int[] score = new int[Integer.MAX_VALUE];


        char ch = 'z';
        boolean result = 'A' <= ch && ch <= 'Z' || 'a' <= ch && ch <= 'z' || '0' <= ch && ch <= '9';
        // System.out.println(result);

        int value = (int) (Math.random() * 6) + 1;
        //System.out.println(value);

        // 0<=x<=10, 0<=y<=10
        for (int x = 0; x <= 10; x++) {
            for (int y = 0; y <= 10; y++) {
                if (2 * x + 4 * y == 10) {
                    System.out.println("x: " + x + ", y: " + y);
                }
            }
        }

    }
}
