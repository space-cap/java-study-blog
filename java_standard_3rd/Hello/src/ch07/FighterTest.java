package ch07;

public class FighterTest {
    public static void main(String[] args) {
        Fighter1 f = new Fighter1();

        if(f instanceof Unit1) {
            System.out.println("f는 Unit1의 자손입니다.");
        }
        if(f instanceof Fightable1) {
            System.out.println("f는 Fightable1의 자손입니다.");
        }
        if(f instanceof Moveable1) {
            System.out.println("f는 Moveable1의 자손입니다.");
        }
        if(f instanceof Attackable1) {
            System.out.println("f는 Attackable1의 자손입니다.");
        }
        if(f instanceof Object) {
            System.out.println("f는 Object의 자손입니다.");
        }
    }
}

class Fighter1 extends Unit1 implements Fightable1 {
    public void move(int x, int y) {}
    public void attack(Unit1 u) {}
}

interface Moveable1 {
    void move(int x, int y);
}
interface Attackable1 {
    void attack(Unit1 u);
}
interface Fightable1 extends Moveable1, Attackable1 {
}

class Unit1 {
    int x, y;
    int currentHP;
}