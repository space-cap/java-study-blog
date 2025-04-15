package ch11;

public class BoxingTest {
    public static void main(String[] args) {
        long start, end;

        // 오토박싱
        start = System.nanoTime();
        Long sum1 = 0L;
        for (long i = 0; i < Integer.MAX_VALUE / 100; i++) {
            sum1 += i;  // 오토박싱 발생 (long → Long)
        }
        end = System.nanoTime();
        System.out.println("오토박싱 시간: " + (end - start) / 1_000_000 + "ms");

        // 기본형
        start = System.nanoTime();
        long sum2 = 0L;
        for (long i = 0; i < Integer.MAX_VALUE / 100; i++) {
            sum2 += i;  // 박싱 없음
        }
        end = System.nanoTime();
        System.out.println("기본형 시간: " + (end - start) / 1_000_000 + "ms");
    }
}
