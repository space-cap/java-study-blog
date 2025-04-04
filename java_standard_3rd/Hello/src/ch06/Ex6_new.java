package ch06;

class Card {
    String kind; // 카드의 무늬
    int number; // 카드의 숫자
    Card(String kind, int number) {
        this.kind = kind;
        this.number = number;

        System.out.println("Card(String,int) Call : " + kind + " " + number);

        
    }
}

public class Ex6_new {
    public static void main(String[] args) {
        Card c = new Card("Heart", 10);
    }
}
