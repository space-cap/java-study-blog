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


        int num1 = 1;
        int num2 = 1;
        int num3 = 0;

        System.out.print(num1);
        System.out.print(num2);
        for (int i = 0; i < 8; i++) {
            num3 = num1 + num2;
            System.out.print(num3 + ",");
            num1 = num2;
            num2 = num3;
        }


    }
}

