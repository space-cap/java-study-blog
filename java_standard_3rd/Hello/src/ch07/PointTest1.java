package ch07;

class PointT1 {
    int x;
    int y;
}

class Point3DT1 extends PointT1 {
    int z;
}

public class PointTest1 {
    public static void main(String[] args) {
        Point3DT1 p3d = new Point3DT1();
    }
}
