package ch08;

public class ExceptionEx17 {
    public static void main(String[] args) {
        try {
            method1();
        } catch(Exception e) {
            System.out.println("Exception caught in main");
            System.out.println("Exception message: " + e.getMessage());
        }
    }

    static void method1() throws Exception {
        try {
            throw new Exception("Exception in method1");
        } catch(Exception e) {
            System.out.println("Exception caught in method1");
            throw e; // Rethrow the exception
        } finally {
            System.out.println("Finally block in method1");
            throw new Exception("Exception in method1 finally");
        }
    }
}
