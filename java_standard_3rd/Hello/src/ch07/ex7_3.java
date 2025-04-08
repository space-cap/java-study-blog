package ch07;

interface Moveable {
    void move(int x, int y);
}

interface Attackable {
    void attack(Unit u);
}
class Unit {
    int x, y;
    int currentHP;
}

public class ex7_3 {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        // Moveable m = new Moveable(); // error : ch07.Moveable is abstract; cannot be instantiated
        Moveable m = new Moveable() {
            @Override
            public void move(int x, int y) {
                System.out.println("Moving to (" + x + ", " + y + ")");
            }
        };
        m.move(10, 20);
    }
}
