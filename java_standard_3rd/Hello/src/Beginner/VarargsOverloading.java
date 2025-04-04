package Beginner;

public class VarargsOverloading {
    public static void printData(String message) {
        System.out.println("Message: " + message);
    }

    public static void printData(String... messages) {
        System.out.print("Varargs: ");
        for (String msg : messages) {
            System.out.print(msg + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printData("Hello");         // Message: Hello (일반 메서드 호출)
        printData("A");
        printData("A", "B");      // Varargs: A, B
        printData("A", "B", "C");   // Varargs: A, Varargs: B, Varargs: C
    }
}
