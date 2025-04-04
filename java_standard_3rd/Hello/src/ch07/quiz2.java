package ch07;

/*
quiz2
5*5 이차원 배열을 받아서 빙고의 개수를 반환하는 메서드 bingoCnt를 작성하시오.
 */

public class quiz2 {
    public static void main(String[] args) {
        int[][] arr = {
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1}
        };

        System.out.println(bingoCnt1(arr));
        System.out.println(bingoCnt2(arr));
        System.out.println(bingoCnt3(arr));
    }

    static int bingoCnt1(int[][] arr) {
        int cnt = 0;
        int sum = 0;

        // 가로
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(i + ", " + j + " : ");
                sum += arr[i][j];
                if (sum >= 5) {
                    cnt++;
                }
            }
            sum = 0;
            System.out.println();
        }

        //System.out.println("====================================");
        // 세로
        for (int j = 0; j < arr[0].length; j++) {
            for (int i = 0; i < arr.length; i++) {
                //System.out.print(i + ", " + j + " : ");
                sum += arr[i][j];
                if (sum >= 5) {
                    cnt++;
                }
            }
            sum = 0;
            //System.out.println();
        }

        //System.out.println("====================================");
        // 우하 대각선
        sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (i == j) {
                    sum += arr[i][j];
                    if (sum >= 5) {
                        cnt++;
                    }
                }
            }
        }

        // 우상 대각선
        sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (i + j == arr.length - 1) {
                    sum += arr[i][j];
                    if (sum >= 5) {
                        cnt++;
                    }
                }
            }
        }

        return cnt;
    }


    static int bingoCnt2(int[][] arr) {
        int cnt = 0;
        int sum = 0;

        // 가로
        for (int i = 0; i < arr.length; i++) {
            sum = 0;
            for (int j = 0; j < arr[i].length; j++) {
                sum += arr[i][j];
            }
            if (sum >= 5) {
                cnt++;
            }
        }

        // 세로
        for (int i = 0; i < arr.length; i++) {
            sum = 0;
            for (int j = 0; j < arr[i].length; j++) {
                sum += arr[j][i];
            }
            if (sum >= 5) {
                cnt++;
            }
        }

        sum = 0;
        // 우하 대각선
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i][i];
        }
        if (sum >= 5) {
            cnt++;
        }

        sum = 0;
        // 우상 대각선
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i][arr.length - 1 - i];
        }
        if (sum >= 5) {
            cnt++;
        }

        return cnt;
    }


    static int bingoCnt3(int[][] arr) {
        int cnt = 0;
        boolean isBingo = true;

        // 가로
        for (int i = 0; i < arr.length; i++) {
            isBingo = true;
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 0) {
                    isBingo = false;
                    break;
                }
            }
            if (isBingo) {
                cnt++;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            isBingo = true;
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[j][i] == 0) {
                    isBingo = false;
                    break;
                }
            }
            if (isBingo) {
                cnt++;
            }
        }

        // 대각선
        boolean isBingo1 = true;
        boolean isBingo2 = true;
        for (int i = 0; i < arr.length; i++) {
            // 우하 대각선
            if (arr[i][i] == 0) {
                isBingo1 = false;
            }

            // 우상 대각선
            if (arr[i][arr.length - 1 - i] == 0) {
                isBingo2 = false;
            }
        }
        if (isBingo1) {
            cnt++;
        }
        if (isBingo2) {
            cnt++;
        }

        return cnt;
    }
}
