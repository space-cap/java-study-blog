package ch07;

/*
quiz1
세 개의 정수를 받아서 중간 크기의 값을 반환하는 mid 메서드를 작성하시오.
 */
public class quiz1 {
    public static void main(String[] args) {
        System.out.println(mid(1, 2, 3));
        System.out.println(mid(2, 1, 3));
        System.out.println(mid(1, 2, 2));
        System.out.println(mid(1, 1, 2));

        System.out.println("=====================");
        System.out.println(mid2(1, 2, 3));
        System.out.println(mid2(2, 1, 3));
        System.out.println(mid2(1, 2, 2));
        System.out.println(mid2(1, 1, 2));
    }

    static int mid(int a, int b, int c) {
        if ((a >= b && a <= c) || (a <= b && a >= c)) {
            return a;
        } else if ((b >= a && b <= c) || (b <= a && b >= c)) {
            return b;
        } else {
            return c;
        }
    }

    static int mid2(int... args) {
        if (args.length != 3) {
            return -1;
        }

        for(int i = 0; i < args.length; i++) {
            for (int j = i + 1; j < args.length; j++) {
                if (args[i] > args[j]) {
                    int temp = args[i];
                    args[i] = args[j];
                    args[j] = temp;
                }
            }
        }

        return args[1];
    }

}
