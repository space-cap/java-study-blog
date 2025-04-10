package quiz;


class Card {
    int num;
    String kind;

    Card(int num, String kind) {
        this.num = num;
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "Card [num=" + num + ", kind=" + kind + "]";
    }
}


class CardDemo {
    final int MAX_CARD = 5;
    final int MAX_KIND = 4;
    final int MAX_NUM = 13;
    final String[] kinds = {"H", "D", "C", "S"}; // Hearts, Diamonds, Clubs, Spades
    Card[] cardArr = new Card[MAX_CARD];

    void InitCards() {
        // Create 5 cards
        for (int i = 0; i < MAX_CARD; i++) {
            int num = (int) (Math.random() * MAX_NUM) + 1;
            int kind = (int) (Math.random() * MAX_KIND);
            cardArr[i] = new Card(num, kinds[kind]);
        }

        // Print the cards
        for (int i = 0; i < MAX_CARD; i++) {
            System.out.println(cardArr[i].toString());
        }
    }

    public String rankCheck() {

        int[] checkNums = new int[MAX_NUM + 1];
        int[] checkKinds = new int[MAX_KIND];

        for (int i = 0; i < MAX_CARD; i++) {
            checkNums[cardArr[i].num]++;
        }

        for (int i = 0; i < MAX_CARD; i++) {
            if (cardArr[i].kind == kinds[0]) {
                checkKinds[0]++;
            } else if (cardArr[i].kind == kinds[1]) {
                checkKinds[1]++;
            } else if (cardArr[i].kind == kinds[2]) {
                checkKinds[2]++;
            } else if (cardArr[i].kind == kinds[3]) {
                checkKinds[3]++;
            }
        }

        // Flush
        boolean isFlush = false;
        for (int i = 0; i < MAX_KIND; i++) {
            if (checkKinds[i] == MAX_CARD) {
                isFlush = true;
            }
        }

        // Straight
        boolean isStraight = false;
        if (cardArr[0].num == cardArr[1].num - 1 &&
                cardArr[1].num == cardArr[2].num - 1 &&
                cardArr[2].num == cardArr[3].num - 1 &&
                cardArr[3].num == cardArr[4].num - 1) {
            isStraight = true;
        }

        if (isFlush && isStraight) {
            return "STRAIGHT FLUSH";
        } else if (isFlush) {
            return "FLUSH";
        } else if (isStraight) {
            return "STRAIGHT";
        }

        // four cards
        for (int i = 0; i < checkNums.length; i++) {
            if (checkNums[i] == 4) {
                return "FOUR CARD";
            }
        }

        // full house
        for (int i = 0; i < checkNums.length; i++) {
            if (checkNums[i] == 3) {
                for (int j = 0; j < checkNums.length; j++) {
                    if (i != j && checkNums[j] == 2) {
                        return "FULL HOUSE";
                    }
                }
            }
        }

        // three cards
        for (int i = 0; i < checkNums.length; i++) {
            if (checkNums[i] == 3) {
                return "THREE CARD";
            }
        }

        // 2 pairs
        for (int i = 0; i < checkNums.length; i++) {
            if (checkNums[i] == 2) {
                for (int j = 0; j < checkNums.length; j++) {
                    if (i != j && checkNums[j] == 2) {
                        return "2 Pair";
                    }
                }
            }
        }

        // 1 pair
        for (int i = 0; i < checkNums.length; i++) {
            if (checkNums[i] == 2) {
                return "1 Pair";
            }
        }

        return "No Rank";
    }
}


public class Poker {
    public static void main(String[] args) {
        System.out.println("Poker");

        CardDemo cardDemo = new CardDemo();
        cardDemo.InitCards();

        String rankName = cardDemo.rankCheck();
        System.out.println("Rank: " + rankName);


    }
}
