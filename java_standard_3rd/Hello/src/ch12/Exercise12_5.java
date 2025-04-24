package ch12;

public class Exercise12_5 {
    public static void main(String[] args) {
        System.out.println("Hello world");

        Deck d = new Deck(); // 카드 한 벌(Deck)을 만든다.
        Card0424 c = d.pick(0); // 섞기 전에 제일 위의 카드를 뽑는다.
        System.out.println(c); // System.out.println(c.toString());과 같다.
        d.shuffle(); // 카드를 섞는다.
        c = d.pick(0); // 섞은 후에 제일 위의 카드를 뽑는다.
        System.out.println(c);
    }
}

class Deck {
    final int CARD_NUM = Card0424.Kind.values().length * Card0424.Number.values().length; // 카드의 개수
    Card0424 cardArr[] = new Card0424[CARD_NUM]; // Card객체 배열을 포함

    Deck() {
/*
(1) 알맞은 코드를 넣어서 완성하시오.
Deck의 카드를 초기화한다.
*/
        int kindCount = Card0424.Kind.values().length;
        int numCount = Card0424.Number.values().length;

        for (int i = 0; i < kindCount; i++) {
            for (int j = 0; j < numCount; j++) {
                cardArr[i * numCount + j].kind = Card0424.Kind.values()[i];
                cardArr[i * numCount + j].num = Card0424.Number.values()[j];
            }
        }

        /* 정답지에 있는 답
        int i=0;
        for(Card0424.Kind kind : Card0424.Kind.values()) {
            for(Card0424.Number num : Card0424.Number.values()) {
                cardArr[i++] = new Card0424(kind, num);
            }
        }
        */

    }

    Card0424 pick(int index) { // 지정된 위치(index)에 있는 카드 하나를 꺼내서 반환
        return cardArr[index];
    }

    Card0424 pick() { // Deck에서 카드 하나를 선택한다.
        int index = (int) (Math.random() * CARD_NUM);
        return pick(index);
    }

    void shuffle() { // 카드의 순서를 섞는다.
        for (int i = 0; i < CARD_NUM; i++) {
            int index = (int) (Math.random() * CARD_NUM);
            Card0424 temp = cardArr[i];
            cardArr[i] = cardArr[index];
            cardArr[index] = temp;
        }
    }
}

// Card클래스
class Card0424 {
    enum Kind {CLOVER, HEART, DIAMOND, SPADE}

    enum Number {
        ACE, TWO, THREE, FOUR, FIVE,
        SIX, SEVEN, EIGHT, NINE, TEN,
        JACK, QUEEN, KING
    }

    Kind kind;
    Number num;

    Card0424() {
        this(Kind.SPADE, Number.ACE);
    }

    Card0424(Kind kind, Number num) {
        this.kind = kind;
        this.num = num;
    }

    public String toString() {
        return "[" + kind.name() + "," + num.name() + "]";
    } // toString()의 끝
} // Card클래스의 끝

