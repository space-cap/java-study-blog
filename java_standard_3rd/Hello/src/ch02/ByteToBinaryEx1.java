package ch02;

public class ByteToBinaryEx1 {
    public static void main(String[] args) {
        byte num = -5; // 예제 값 (00000101)

        // Integer.toBinaryString() 사용
        String binary = String.format("%8s", Integer.toBinaryString(num & 0xFF)).replace(' ', '0');

        System.out.println("이진수: " + binary);
    }
}

