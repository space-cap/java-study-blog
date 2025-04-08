package ch07;

class Card {
    final int NUMBER = 99; // 상수지만 선언과 함께 초기화 하진 않고
    final String KIND; // 생성자에서 단 한번만 초기화할 수 있다.
    static int weight = 100; // 카드의 폭
    static int height = 250; // 카드의 높이

    Card(String kind, int number) {
        this.KIND = kind;
        // this.NUMBER = number;
    }

    Card() {
        this("HEART", 1);
    }

    public String toString() {
        return "kind : " + KIND + ", number : " + NUMBER;
    }
}


public class FinalCardTest {
    public static void main(String[] args) {
        Card c = new Card("HEART", 10);
        // c.NUMBER = 5; // final 변수는 값을 변경할 수 없다. //cannot assign a value to final variable NUMBER
        System.out.println(c.KIND);
        System.out.println(c.NUMBER);
        System.out.println(c); // toString() 메소드 호출
    }
}
