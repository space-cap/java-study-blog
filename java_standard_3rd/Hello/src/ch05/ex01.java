package ch05;

public class ex01 {
    static public void main(String[] args) {
        // int i = Integer.MAX_VALUE;
        // int[] score = new int[Integer.MAX_VALUE];


        char ch = 'z';
        boolean result = 'A' <= ch && ch <= 'Z' || 'a' <= ch && ch <= 'z' || '0' <= ch && ch <= '9';
        System.out.println(result);

        int sum = 0;
        for (int i = 1; ; i++) {
            sum += i;
            if (i % 2 == 0) {
                sum += -i;
            }
            if (sum > 100) {
                System.out.println(i);
                break;
            }
        }


    }
}
