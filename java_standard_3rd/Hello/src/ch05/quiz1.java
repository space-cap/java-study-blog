package ch05;

public class quiz1 {
    public static void main(String[] args) {

        int[][] arr = new int[2][3];

        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                arr[i][j] = (int)(Math.random() * 9) + 1;
            }
        }

        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        int s = 0;
        int b = 0;
        if(arr[0][0] == arr[1][0]) {
            s++;
        }
        else {
            b++;
        }

        if(arr[0][1] == arr[1][1]) {
            s++;
        }
        else {
            b++;
        }

        if(arr[0][2] == arr[1][2]) {
            s++;
        }
        else {
            b++;
        }

        System.out.println("s: " + s);
        System.out.println("b: " + b);
    }
}
