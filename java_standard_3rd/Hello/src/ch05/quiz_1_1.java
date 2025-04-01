package ch05;

public class quiz_1_1 {
    public static void main(String[] args) {
        int size = 3;
        int[] arr = new int[size];
        int[] arr2 = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = (int)(Math.random() * 10);
            arr2[i] = (int)(Math.random() * 10);
        }

        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print(arr2[i] + " ");
        }

        int s = 0;
        int b = 0;
        for (int i = 0; i < size; i++) {
            if (arr[i] == arr2[i]) {
                s++;
            }
            else {
                b++;
            }
        }

        System.out.println();
        System.out.println("s: " + s);
        System.out.println("b: " + b);
    }
}
