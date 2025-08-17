package ch13;

public class ch13_3_2 {
    public static void main(String[] args) {
        Runnable r = () -> {System.out.println("Hello World");};
        Thread t = Thread.ofPlatform().start(r);
        

    }
}
