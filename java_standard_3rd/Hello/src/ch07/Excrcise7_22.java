package ch07;

abstract class Shape722 {
    Point722 p;

    Shape722() {
        this(new Point722(0,0));
    }

    Shape722(Point722 p) {
        this.p = p;
    }

    abstract double calcArea();

    Point722 getPosition() {
        return p;
    }

    void setPosition(Point722 p) {
        this.p = p;
    }
}

class Point722 {
    int x, y;

    Point722() {
        this(0, 0);
    }

    Point722(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}

class Circle722 extends Shape722 {
    double r; // 반지름

    Circle722(double r) {
        this(0, 0, r);
    }

    Circle722(int x, int y, double r) {
        super(new Point722(x, y));
        this.r = r;
    }

    @Override
    double calcArea() {
        return Math.PI * r * r;
    }
}


class Rectangle722 extends Shape722 {
    double width, height;

    Rectangle722(double width, double height) {
        this(0, 0, width, height);
    }

    Rectangle722(int x, int y, double width, double height) {
        super(new Point722(x, y));
        this.width = width;
        this.height = height;
    }

    @Override
    double calcArea() {
        return width * height;
    }

    boolean isSquare() {
        return width == height;
    }
}

public class Excrcise7_22 {
    public static void main(String[] args) {
        System.out.println("main hello");

        Shape722[] arr = {
            new Circle722(5.0),
            new Rectangle722(3,4),
            new Circle722(1)
        };
        System.out.println("면적의 합: " + sumArea(arr));
    }

    static double sumArea(Shape722[] arr) {
        double sum = 0;
        for (Shape722 s : arr) {
            sum += s.calcArea();
        }
        return sum;
    }
}


