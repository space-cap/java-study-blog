package ch05;

public class ex02 {
    public static void main(String[] args) {

        String value = "123";
        char ch = ' ';
        boolean isNumber = false;

        for(int i = 0; i < value.length(); i++) {
            ch = value.charAt(i);
            if (ch >= '0' && ch <= '9') {
                isNumber = true;
                break;
            }
        }

        if (isNumber) {
            System.out.println("The string contains a number.");
        } else {
            System.out.println("The string does not contain a number.");
        }
    }
}
