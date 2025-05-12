package mini_project.pokergame1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PokerGame {
    public static void main(String[] args) {
        // 플레이어들 생성
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");

        // 덱 생성 및 섞기
        Deck deck = new Deck();

        // 각 플레이어에게 카드 5장씩 나누기
        p1.setHand(deck.drawHand(5));
        p2.setHand(deck.drawHand(5));

        // 플레이어 핸드 출력
        p1.printHand();
        p2.printHand();

        // 각 플레이어의 핸드 족보 판별
        p1.setHandRank(PokerHandEvaluator.evaluateHand(p1.getHand()));
        p2.setHandRank(PokerHandEvaluator.evaluateHand(p2.getHand()));

        // 각 플레이어의 카드 순위 평가 (동급 족보 비교용)
        p1.setHandStrength(evaluateHandStrength(p1.getHand(), p1.getHandRank()));
        p2.setHandStrength(evaluateHandStrength(p2.getHand(), p2.getHandRank()));

        // 승자 비교
        PokerHandComparator comparator = new PokerHandComparator();
        int result = comparator.compare(p1, p2);

        // 결과 출력
        if (result > 0) {
            System.out.println(p1.getName() + " wins!");
        } else if (result < 0) {
            System.out.println(p2.getName() + " wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    // 각 플레이어의 카드 순위 평가 (강한 카드 순으로 정렬)
    private static List<Integer> evaluateHandStrength(List<Card> hand, HandRank handRank) {
        List<Integer> handStrength = new ArrayList<>();

        // 족보별로 강한 카드 순으로 리스트 반환
        switch (handRank) {
            case ROYAL_FLUSH:
            case STRAIGHT_FLUSH:
            case FLUSH:
            case STRAIGHT:
                for (Card card : hand) {
                    handStrength.add(card.getRank().getValue());
                }
                Collections.sort(handStrength, Collections.reverseOrder()); // 큰 값이 앞에 오도록 정렬
                break;

            case FOUR_OF_A_KIND:
            case FULL_HOUSE:
                // 4장 혹은 3장 같은 카드 + 나머지 카드
                Map<Integer, Long> rankCount = hand.stream()
                        .collect(Collectors.groupingBy(card -> card.getRank().getValue(), Collectors.counting()));

                rankCount.entrySet().stream()
                        .filter(entry -> entry.getValue() > 1)
                        .sorted((entry1, entry2) -> Long.compare(entry2.getValue(), entry1.getValue()))
                        .forEach(entry -> handStrength.add(entry.getKey()));

                break;

            case THREE_OF_A_KIND:
            case TWO_PAIR:
            case ONE_PAIR:
                // 3장, 2페어, 원페어는 해당되는 카드들을 리스트에 추가
                rankCount = hand.stream()
                        .collect(Collectors.groupingBy(card -> card.getRank().getValue(), Collectors.counting()));

                rankCount.entrySet().stream()
                        .filter(entry -> entry.getValue() > 1)
                        .sorted((entry1, entry2) -> Long.compare(entry2.getValue(), entry1.getValue()))
                        .forEach(entry -> handStrength.add(entry.getKey()));

                // 나머지 카드들을 추가
                hand.stream()
                        .filter(card -> !handStrength.contains(card.getRank().getValue()))
                        .forEach(card -> handStrength.add(card.getRank().getValue()));
                break;

            case HIGH_CARD:
                // 탑카드는 단순히 숫자 큰 순으로 정렬
                for (Card card : hand) {
                    handStrength.add(card.getRank().getValue());
                }
                Collections.sort(handStrength, Collections.reverseOrder());
                break;
        }

        return handStrength;
    }
}
