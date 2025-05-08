package pattern_ex1;

import java.util.*;

interface GameState {
    void handle(PokerGame game); // 상태별 행동
    String getName(); // 상태 이름 (FSM에서 사용)
}

class DealCardsState implements GameState {
    public void handle(PokerGame game) {
        System.out.println("각 플레이어에게 카드 5장씩 나눠줍니다!");
        game.dealCards(); // 실제 카드 나눠주는 로직
    }

    public String getName() {
        return "DealCards";
    }
}


class FirstBettingState implements GameState {
    public void handle(PokerGame game) {
        System.out.println("첫 번째 배팅 라운드 시작!");
        game.processBetting(); // 배팅 처리 로직
    }

    public String getName() {
        return "FirstBetting";
    }
}


class DrawCardsState implements GameState {
    public void handle(PokerGame game) {
        System.out.println("플레이어가 카드를 교체합니다!");
        game.drawCards(); // 카드 교체 로직
    }

    public String getName() {
        return "DrawCards";
    }
}


class SecondBettingState implements GameState {
    public void handle(PokerGame game) {
        System.out.println("두 번째 배팅 라운드 시작!");
        game.processBetting(); // 배팅 처리 로직
    }

    public String getName() {
        return "SecondBetting";
    }
}


class ShowdownState implements GameState {
    public void handle(PokerGame game) {
        System.out.println("카드를 공개하고 승자를 결정합니다!");
        game.determineWinner(); // 승자 결정 로직
    }

    public String getName() {
        return "Showdown";
    }
}

class GameOverState implements GameState {
    public void handle(PokerGame game) {
        System.out.println("게임 종료! 상금을 나눠줍니다!");
        game.distributePot(); // 상금 분배 로직
    }

    public String getName() {
        return "GameOver";
    }
}




class StateMachine {
    private Map<String, GameState> states = new HashMap<>();
    private Map<String, String> transitions = new HashMap<>();
    private String currentState;

    public StateMachine() {
        // 상태 등록
        states.put("DealCards", new DealCardsState());
        states.put("FirstBetting", new FirstBettingState());
        states.put("DrawCards", new DrawCardsState());
        states.put("SecondBetting", new SecondBettingState());
        states.put("Showdown", new ShowdownState());
        states.put("GameOver", new GameOverState());

        // 상태 전환 테이블
        transitions.put("DealCards", "FirstBetting");
        transitions.put("FirstBetting", "DrawCards");
        transitions.put("DrawCards", "SecondBetting");
        transitions.put("SecondBetting", "Showdown");
        transitions.put("Showdown", "GameOver");
        transitions.put("GameOver", "GameOver"); // 종료 후 정지

        currentState = "DealCards"; // 초기 상태
    }

    public GameState getCurrentState() {
        return states.get(currentState);
    }

    public void transition(PokerGame game) {
        String nextState = transitions.get(currentState);
        // 조건 기반 전환 예: 배팅 라운드에서 모두 폴드하면 바로 Showdown
        if (currentState.equals("FirstBetting") || currentState.equals("SecondBetting")) {
            if (game.getActivePlayersCount() <= 1) {
                nextState = "Showdown";
            }
        }
        currentState = nextState;
    }
}




class PokerGame {
    private StateMachine stateMachine;
    private List<Player> players;
    private Deck deck;
    private int pot;

    public PokerGame(int numPlayers) {
        stateMachine = new StateMachine();
        players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player("Player " + (i + 1)));
        }
        deck = new Deck();
        pot = 0;
    }

    public void nextStep() {
        GameState currentState = stateMachine.getCurrentState();
        currentState.handle(this); // 현재 상태의 행동 실행
        stateMachine.transition(this); // 다음 상태로 전환
    }

    // 상태별 로직 (간단히 예시)
    public void dealCards() {
        deck.shuffle();
        for (Player player : players) {
            player.receiveCards(deck.deal(5));
        }
    }

    public void processBetting() {
        // 배팅 로직: 각 플레이어가 배팅/폴드 선택
        for (Player player : players) {
            if (player.isActive()) {
                // 예: 플레이어가 배팅 (임시로 고정 금액)
                int bet = 10;
                pot += bet;
                player.bet(bet);
            }
        }
    }

    public void drawCards() {
        // 카드 교체 로직: 각 플레이어가 교체할 카드 선택
        for (Player player : players) {
            if (player.isActive()) {
                // 예: 2장 교체 (임시)
                List<Card> discarded = player.discard(2);
                player.receiveCards(deck.deal(discarded.size()));
            }
        }
    }

    public void determineWinner() {
        Player winner = null;
        for (Player player : players) {
            if (player.isActive()) {
                if (winner == null || player.getHand().compareTo(winner.getHand()) > 0) {
                    winner = player;
                }
            }
        }
        if (winner != null) {
            System.out.println(winner.getName() + " 승리!");
        }
    }

    public void distributePot() {
        // 상금 분배 로직 (간단히)
        System.out.println("상금 " + pot + " 분배!");
        pot = 0;
    }

    public int getActivePlayersCount() {
        int count = 0;
        for (Player player : players) {
            if (player.isActive()) count++;
        }
        return count;
    }
}



class Player {
    private String name;
    private List<Card> hand;
    private boolean active;
    private int chips;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.active = true;
        this.chips = 100; // 초기 칩
    }

    public void receiveCards(List<Card> cards) {
        hand.addAll(cards);
    }

    public List<Card> discard(int count) {
        List<Card> discarded = new ArrayList<>();
        for (int i = 0; i < count && !hand.isEmpty(); i++) {
            discarded.add(hand.remove(0));
        }
        return discarded;
    }

    public void bet(int amount) {
        chips -= amount;
    }

    public boolean isActive() {
        return active;
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return new Hand(hand); // Hand는 카드 조합 평가 클래스
    }
}

class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        // 카드 초기화 (52장)
        for (String suit : new String[]{"Hearts", "Diamonds", "Clubs", "Spades"}) {
            for (int rank = 1; rank <= 13; rank++) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public List<Card> deal(int count) {
        List<Card> dealt = new ArrayList<>();
        for (int i = 0; i < count && !cards.isEmpty(); i++) {
            dealt.add(cards.remove(0));
        }
        return dealt;
    }
}

class Card {
    private int rank;
    private String suit;

    public Card(int rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

class Hand {
    private List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public int compareTo(Hand other) {
        // 핸드 평가 로직 (예: 포커 랭킹 비교)
        return 0; // 임시
    }
}







public class PockerGameEx1 {
    public static void main(String[] args) {
        System.out.println("Hello, PockerGameEx1");

        PokerGame game = new PokerGame(4); // 4명 플레이어
        game.nextStep(); // "각 플레이어에게 카드 5장씩 나눠줍니다!"
        game.nextStep(); // "첫 번째 배팅 라운드 시작!"
        game.nextStep(); // "플레이어가 카드를 교체합니다!"
        game.nextStep(); // "두 번째 배팅 라운드 시작!"
        game.nextStep(); // "카드를 공개하고 승자를 결정합니다!"
        game.nextStep(); // "게임 종료! 상금을 나눠줍니다!"

    }
}
