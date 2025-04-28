package ch04;

public class StarEx14 {
    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            int k = 0;

            if (i < 5) {
                k = i % 5;
            } else {
                k = 3 - (i % 5);
            }

            for (int j = 0; j <= k; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
