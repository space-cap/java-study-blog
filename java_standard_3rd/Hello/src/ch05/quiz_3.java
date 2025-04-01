package ch05;

public class quiz_3 {
    public static void main(String[] args) {
        // 9 8 7 6 5 4 3 2 1
        for (int i = 1; i <= 9; i++) {
            System.out.print(10 - i + " ");
        }

        System.out.println();
        for (int i = 1; i <= 9; i++) {
            System.out.print(i * 2 + " ");
        }

        System.out.println();
        for (int i = 1; i <= 9; i++) {
            System.out.print(i + (i - 1) + " ");
        }

        System.out.println();
        for (int i = 1; i <= 9; i++) {
            System.out.print(i * i + " ");
        }

        // 5.
        System.out.println();
        for (int i = 1; i <= 9; i++) {
            System.out.print(i % 3 == 0 ? 3 + " " : i % 3 + " ");
        }

        // 6.
        System.out.println();
        for (int i = 1; i <= 9; i++) {
            System.out.print(i % 3 == 0 ? i / 3 + " " : (i / 3) + 1 + " ");
        }

        
    }
}
