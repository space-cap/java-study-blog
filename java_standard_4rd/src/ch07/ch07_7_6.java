package ch07;


interface Fightable {

}

class Fighter implements Fightable {}


public class ch07_7_6 {
    public static void main(String[] args) {
        System.out.println("Hello World");

        Fightable fighter = new Fighter();
    }
}
