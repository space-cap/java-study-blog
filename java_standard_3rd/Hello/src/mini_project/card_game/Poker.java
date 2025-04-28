package mini_project.card_game;

import java.util.*;

class Card {
    private int num;
    private String kind; // 문양

    Card(int num, String kind) {
        this.num = num;
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "Card [num=" + num + ", kind=" + kind + "]";
    }
}


class Player {
    private String nickName;
    private int gameMoney;
    Card[] cards = new Card[5];

    Player(String nickName, int gameMoney) {
        this.nickName = nickName;
        this.gameMoney = gameMoney;
    }

    public void setNickName(String nickName) {
        if (nickName.length() > 20 || nickName.isEmpty()) {
            return;
        }
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public int getGameMoney() {
        return gameMoney;
    }

    @Override
    public String toString() {
        return "Player [nickName=" + nickName + " gameMoney=" + gameMoney + " cards=" + cards.length + "]";
    }
}

public class Poker {
    public static void main(String[] args) {
        System.out.println("Poker");


        // 1. 게임 당 한벌의 카드만 사용. 카드 한벌은 서로 다른 52장의 카드로 구성.
        int cardCount = 52;
        int maxNum = 13; // 카드 번호 1 ~ 13 (J:11, Q:12, K:13)
        char[] kinds = {'S', 'H', 'C', 'D'}; // Hearts, Diamonds, Clubs, Spades
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < kinds.length; i++) {
            for (int j = 0; j < maxNum; j++) {
                cards.add(new Card(j + 1, kinds[i] + ""));
            }
        }

        for (var card : cards) {
            System.out.println(card);
        }

        //2. 카드게임은 최대 4명의 플레이어가 참가 가능
        //3. 각 플레이어에게는 게임머니 10000원이 제공된다.
        //4. 각 플레이어는 자신만의 고유한 nickname을 가지며 nickname의 길이는 20자를 넘지못한다.
        int playerCount = 4; // 총 플레이어
        int gameMoney = 10000; // 게임머니
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < playerCount; i++) {
            players.add(new Player("Player" + i, gameMoney));
        }

        for (var player : players) {
            System.out.println(player);
        }


        // 5. 딜러는 플레이어에게 서로 *다른 5장의 카드를* 나눠준다.
        // 카드를 섞는다. shuffle
        var arr = cards.toArray();
        for (int i = 0; i < arr.length; i++) {
            int rand = (int) (Math.random() * arr.length);
            Card tmp = (Card) arr[i];
            arr[i] = arr[rand];
            arr[rand] = tmp;
        }

        Queue<Card> shuffleCards = new ArrayDeque<>();
        var tmpCards = Arrays.stream(arr).toList();
        for (var card : tmpCards) {
            shuffleCards.add((Card) card);
        }

        for (int i = 0; i < playerCount; i++) {
            for (int j = 0; j < 5; j++) {
                var card = shuffleCards.poll();
                players.get(i).cards[j] = card;
            }
        }

        int size = shuffleCards.size();
        System.out.println("remain card: " + size);

        //6. 딜러는 플레이어의 카드를 평가하고 결과를 점수로 반환한다.(점수가 높을 수록 좋음)
        //7. 카드의 평가는 일반적인 포커의 랭크를 참고하여 높은 랭크에게 더 높은 점수를 준다.
        //8. 매 게임마다 딜러는 각 플레이어의 카드를 평가하여 결과를 출력한다.

        


    }
}
