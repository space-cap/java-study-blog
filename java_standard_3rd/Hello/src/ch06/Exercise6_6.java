package ch06;

public class Exercise6_6 {
    static double getDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public static void main(String[] args) {
        System.out.println("Distance: " + getDistance(1, 1, 2, 2));
    }
}
