package ch07;

public class PointTest {
    public static void main(String[] args) {
        Point3D P3 = new Point3D(1, 2, 3);
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        System.out.println("Point(x,y) constructor called");
        this.x = x;
        this.y = y;
    }

    void showPoint() {
        System.out.println("x: " + x + ", y: " + y);
    }
}

class Point3D extends Point {
    int z;

    Point3D(int x, int y, int z) {
        super(x, y); // Call the constructor of the superclass (Point)
        System.out.println("Point3D(x,y,z) constructor called");

        //this.x = x;
        //this.y = y;
        this.z = z;


    }

    void showPoint() {
        System.out.println("x: " + x + ", y: " + y + ", z: " + z);
    }
}