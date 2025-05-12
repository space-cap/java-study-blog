package mini_project.pokergame1;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;
    private HandRank handRank; // 족보 평가 결과
    private List<Integer> handStrength; // 동급 족보 비교용 정렬된 숫자들

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public HandRank getHandRank() {
        return handRank;
    }

    public void setHandRank(HandRank handRank) {
        this.handRank = handRank;
    }

    public List<Integer> getHandStrength() {
        return handStrength;
    }

    public void setHandStrength(List<Integer> handStrength) {
        this.handStrength = handStrength;
    }

    public void printHand() {
        System.out.println(name + "'s hand:");
        for (Card card : hand) {
            System.out.println("  " + card);
        }
    }
}

