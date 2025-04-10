package mentor;

import java.util.Scanner;

class Calculator {
    void run() {
        System.out.println("Calculator is running");

        Scanner scanner = new Scanner(System.in);

        System.out.print("문자열을 입력하세요: ");
        String input = scanner.nextLine();

        System.out.println("입력한 문자열: " + input);

        scanner.close();

        input = input.trim().replaceAll(" ", "");
        System.out.println("입력한 문자열: " + input);
        System.out.println("문자열 길이: " + input.length());

        String[] singleWords = input.trim().split("");
        for(int i = 0; i < singleWords.length-2; i+=2) {
            char ch1 = singleWords[i].charAt(0);
            char ch2 = singleWords[i+1].charAt(0);
            char ch3 = singleWords[i+2].charAt(0);
            int result = cal(ch1, ch2, ch3, i);
            System.out.println("result: " + result);
        }
    }

    int result = 0;
    int cal(char ch1, char ch2, char ch3, int idx) {
        System.out.println(ch1 + " " + ch2 + " " + ch3);

        int num1 = Character.getNumericValue(ch1);
        int num2 = Character.getNumericValue(ch3);

        if(idx != 0) {
            num1 = result;
        }

        if (ch2 == '+') {
            result = add(num1, num2);
        } else if (ch2 == '-') {
            result = subtract(num1, num2);
        } else if (ch2 == '*') {
            result = multiply(num1, num2);
        } else if (ch2 == '/') {
            result = divide(num1, num2);
        }

        return result;
    }

    int add(int a, int b) {
        return a + b;
    }

    int subtract(int a, int b) {
        return a - b;
    }

    int multiply(int a, int b) {
        return a * b;
    }

    int divide(int a, int b) {
        if (b == 0) {
            System.out.println("0으로 나눌 수 없습니다.");
            return 0;
        }
        return a / b;
    }

}



public class Homework0410 {
    public static void main(String[] args) {
        System.out.println("Homework0410");

        Calculator calculator = new Calculator();
        calculator.run();
    }
}
