package ch06;

public class Exercise6_2 {
    public static void main(String[] args) {
        SutdaCard card1 = new SutdaCard(3, false);
        SutdaCard card2 = new SutdaCard();

        System.out.println(card1.info()); // 3K
        System.out.println(card2.info()); // 1K
    }
}

class SutdaCard {
    int num = 0;
    boolean isKwang = false;

    SutdaCard() {
        this(1, true);
    }
    SutdaCard(int num, boolean isKwang) {
        this.num = num;
        this.isKwang = isKwang;
    }

    public String info() {
        return num + (isKwang ? "K" : "");
    }
}