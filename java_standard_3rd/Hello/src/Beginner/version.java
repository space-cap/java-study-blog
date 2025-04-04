package Beginner;

public class version {
    public static void main(String[] args) {
        var version =  Runtime.version().feature();
        System.out.println("👋 Hello, Java " + version);
    }
}
