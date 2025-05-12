package mini_project.pokergame1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) throw new IllegalStateException("No more cards!");
        return cards.remove(0);
    }

    public List<Card> drawHand(int count) {
        List<Card> hand = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            hand.add(drawCard());
        }
        return hand;
    }
}
