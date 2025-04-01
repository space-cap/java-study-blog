package ch05;

public class ex01 {
    static public void main(String[] args) {
        // int i = Integer.MAX_VALUE;
        // int[] score = new int[Integer.MAX_VALUE];


        char ch = 'z';
        boolean result = 'A' <= ch && ch <= 'Z' || 'a' <= ch && ch <= 'z' || '0' <= ch && ch <= '9';
        System.out.println(result);
    }
}
