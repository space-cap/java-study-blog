package ch02;

public class precisionEx1 {
    public static void main(String[] args) {
        float f1 = 3.1415926535F;
        float f2 = 123456.7891234F;
        System.out.println(f1); // 출력: 3.1415927 (소수점 7자리까지만 정확)
        System.out.println(f2); // 출력: 123456.79 (정밀도 초과로 반올림)


    }
}
