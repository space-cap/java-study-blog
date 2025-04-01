package ch05;

public class quiz_2 {
    public static void main(String[] args) {
        int[] arr = {3, 4, 6, 1, 2, 5, 1, 2, 3, 2, 5};

        // 중복제거
        int[] overlap = new int[100];
        for (int i = 0; i < arr.length; i++) {
            overlap[arr[i]] += 1;
        }

        for (int i = 0; i < overlap.length; i++) {
            if (overlap[i] > 0) {
                System.out.print(i + ", ");
            }
        }

        // 정렬
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }

                System.out.println();
                for (int k = 0; k < arr.length; k++) {
                    System.out.print(arr[k] + ", ");
                }
            }
        }

        System.out.println();
        for (int j : arr) {
            System.out.print(j + ", ");
        }

    }
}
