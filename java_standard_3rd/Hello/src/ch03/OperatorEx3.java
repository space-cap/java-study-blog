package ch03;

public class OperatorEx3 {
    public static void main(String[] args) {

        int x = 5;

        x = x++ - ++x; // x의 값은?

        System.out.println(x);
    }
}
