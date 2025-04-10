package ch08;

public class StringTest {
    public static void main(String[] args) {
        String str1 = "hello";
        System.out.println(str1);

        changeText(str1);
        System.out.println(str1);
    }

    static void changeText(String str) {
        str = "world";
    }
}
