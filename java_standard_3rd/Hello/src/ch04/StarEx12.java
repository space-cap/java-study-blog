package ch04;

public class StarEx12 {
    public static void main(String[] args) {
        System.out.println("  *  ");

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                if (j == 0 || i == j || i + j == 8 || (j==1 && i>1&& i<8) || (j==2 && i>2&&i<7) || (j==3 && i>3&&i<6)){
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
