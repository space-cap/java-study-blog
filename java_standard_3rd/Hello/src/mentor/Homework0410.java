package mentor;

import java.util.Scanner;

class Calculator {
    int calculate(char ch1, char ch2, char ch3, int idx, int preResult) {
        int num1 = Character.getNumericValue(ch1);
        int num2 = Character.getNumericValue(ch3);

        if(idx != 0) {
            num1 = preResult;
        }

        System.out.print(num1 + " " + ch2 + " " + num2);

        int result = 0;
        if (ch2 == '+') {
            result =  add(num1, num2);
        } else if (ch2 == '-') {
            result =  subtract(num1, num2);
        } else if (ch2 == '*') {
            result =  multiply(num1, num2);
        } else if (ch2 == '/') {
            result =  divide(num1, num2);
        }

        System.out.println(" = " + result);
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
        return a / b;
    }
}


public class Homework0410 {
    public static void main(String[] args) {
        System.out.println("Homework0410");

        Calculator calculator = new Calculator();

        Scanner scanner = new Scanner(System.in);
        System.out.print("문자열을 입력하세요: ");
        String input = scanner.nextLine();
        scanner.close();

        input = input.trim().replaceAll(" ", "");
        System.out.println("입력한 문자열: " + input);

        String[] singleWords = input.trim().split("");
        int result = 0;
        for(int i = 0; i < singleWords.length-2; i+=2) {
            char ch1 = singleWords[i].charAt(0);
            char ch2 = singleWords[i+1].charAt(0);
            char ch3 = singleWords[i+2].charAt(0);
            result = calculator.calculate(ch1, ch2, ch3, i, result);
        }

        System.out.println("result: " + result);
    }
}
