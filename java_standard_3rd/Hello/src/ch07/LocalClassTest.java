package ch07;

public class LocalClassTest {
    public Runnable getRunnable() {
        int localVar = 42; // 지역 변수

        // 지역 변수 localVar를 참조하는 지역 클래스
        class LocalPrinter implements Runnable {
            public void run() {
                System.out.println("localVar = " + localVar);
            }
        }

        return new LocalPrinter(); // 내부 클래스 인스턴스 반환
    }

    public static void main(String[] args) {
        Runnable r = new LocalClassTest().getRunnable(); // getRunnable() 끝남 → localVar는 스택에서 사라짐
        r.run(); // 하지만 여기서 localVar를 쓰려고 함 → ⚠️



        // 이 코드는 컴파일은 되지만,
        // localVar가 절대 바뀌면 안 되는 이유를 보여주는 구조야.
    }
}
