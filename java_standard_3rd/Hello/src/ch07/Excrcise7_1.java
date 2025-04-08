package ch07;

class SutdaDeck {
    final int CARD_NUM = 20;
    SutdaCard[] cards = new SutdaCard[CARD_NUM];

    SutdaDeck() {
        for (int i = 0; i < CARD_NUM; i++) {
            if (i < 10) {
                int num = i + 1;
                cards[i] = new SutdaCard(num, (num == 1 || num == 3 || num == 8));
            } else {
                cards[i] = new SutdaCard(i - 9, false);
            }
        }
    }

    // 7_2
    void shuffle() {
        for (int i = 0; i < cards.length; i++) {
            int randomNum = (int) (Math.random() * cards.length);
            SutdaCard temp = cards[i];
            cards[i] = cards[randomNum];
            cards[randomNum] = temp;
        }
    }

    SutdaCard pick(int index) {
        return cards[index];
    }

    SutdaCard pick() {
        int rndIdx = (int) (Math.random() * cards.length);
        return cards[rndIdx];
    }
}

class SutdaCard {
    int num;
    boolean isKwang;

    SutdaCard() {
        this(1, true);
    }

    SutdaCard(int num, boolean isKwang) {
        this.num = num;
        this.isKwang = isKwang;
    }

    public String toString() {
        return num + (isKwang ? "K" : "");
    }
}

public class Excrcise7_1 {
    public static void main(String[] args) {
        SutdaDeck deck = new SutdaDeck();

        for (int i = 0; i < deck.cards.length; i++) {
            System.out.print(deck.cards[i] + ", ");
        }
    }
}
