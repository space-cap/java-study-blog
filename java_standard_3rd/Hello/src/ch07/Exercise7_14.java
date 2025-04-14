package ch07;

class SutdaCard_7_14 {
    final int num;
    final boolean isKwang;

    private SutdaCard_7_14() {
        this(1, true);
    }

    SutdaCard_7_14(int num, boolean isKwang) {
        this.num = num;
        this.isKwang = isKwang;
    }

    @Override
    public String toString() {
        return num + (isKwang ? "K" : "");
    }
}

public class Exercise7_14 {
    public static void main(String[] args) {
        SutdaCard_7_14 card = new SutdaCard_7_14(1, true);
        System.out.println(card.toString());

        //card.num = 2;
        System.out.println(card.toString());

    }
}
