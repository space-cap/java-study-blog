package ch04;

public class StarEx1 {
    public static void main(String[] args) {
        // 별찍기
        /*
        *****
        *****
        *****
        *****
        *****
        */

        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
