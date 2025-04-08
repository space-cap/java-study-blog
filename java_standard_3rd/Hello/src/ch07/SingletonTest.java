package ch07;

final class Singleton {
    private static Singleton s = new Singleton();

    // Private constructor to prevent instantiation
    private Singleton() {
        System.out.println("Singleton() instance created");
    }

    // Public method to provide access to the instance
    public static Singleton getInstance() {
        if (s == null) {
            s = new Singleton();
        }
        return s;
    }
}

public class SingletonTest {
    public static void main(String[] args) {
        // Singleton s1 = new Singleton(); // error : Singleton() has private access in ch07.Singleton
        Singleton s = Singleton.getInstance();
    }
}
