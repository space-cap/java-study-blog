package ch06;

public class Exercise6_23 {

    static int max(int[] arr) {
        if(arr == null || arr.length == 0) {
            return -999999; // 배열이 null이거나 크기가 0인 경우
        }

        int max = arr[0]; // 배열의 첫 번째 요소를 최대값으로 초기화
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] > max) {
                max = arr[i]; // 현재 요소가 최대값보다 크면 최대값을 갱신
            }
        }

        return max;
    }

    static int max2(int[] arr) {
        if(arr == null || arr.length == 0) {
            return -999999; // 배열이 null이거나 크기가 0인 경우
        }

        int max = 0;
        for(int i = 0;i<arr.length;i++) {
            if(arr[i] > max) {
                max = arr[i];
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] data = {3, 2, 9, 4, 7};
        System.out.println(java.util.Arrays.toString(data));
        System.out.println("최대값:" + max(data));
        System.out.println("최대값:" + max(null));
        System.out.println("최대값:" + max(new int[]{})); // 크기가 0인 배열
    }
}
