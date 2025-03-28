package ch04;

public class FlowEx7 {
    public static void main(String[] args) {
        int com;

        var rnd = Math.random();
        System.out.println(rnd);

        for(int i = 1; i <= 5; i++) {
            rnd = Math.random();
            System.out.println(rnd);
        }

        for(int i = 1; i <= 50; i++) {
            com = (int)(Math.random() * 6);
            System.out.println(com);
        }

        // 0.0은 범위에 포함되고 1.0은 범위에 포함되지 않음.
        // 0.0 <= Math.random() < 1.0
    }
}
