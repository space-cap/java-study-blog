package ch13;

public class Exercise13_1 {
    public static void main(String[] args) throws InterruptedException {
        Thread1 th1 = new Thread1();
        th1.start();

        System.out.println();
        Thread.sleep(1000);

        Runnable r = new Thread2();
        Thread th2 = new Thread(r);
        th2.start();

    }
}

class Thread1 extends Thread {
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print('-');
        }
        System.out.println();
    }
}

class Thread2 implements Runnable {
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print('+');
        }
        System.out.println();
    }
}

