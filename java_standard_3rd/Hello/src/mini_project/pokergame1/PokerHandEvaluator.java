package mini_project.pokergame1;

import java.util.*;
import java.util.stream.Collectors;

public class PokerHandEvaluator {

    // 핸드의 족보를 판별하여 HandRank 반환
    public static HandRank evaluateHand(List<Card> hand) {
        // 카드 정렬: 먼저 숫자값 기준, 같은 숫자면 무늬로 정렬
        hand.sort(Comparator.comparing(Card::getRank)
                .thenComparing(Card::getSuit));

        // 카드의 숫자 값과 무늬만 분리
        List<Integer> ranks = new ArrayList<>();
        List<Suit> suits = new ArrayList<>();
        for (Card card : hand) {
            ranks.add(card.getRank().getValue());
            suits.add(card.getSuit());
        }

        // 족보 체크
        if (isRoyalFlush(ranks, suits)) return HandRank.ROYAL_FLUSH;
        if (isStraightFlush(ranks, suits)) return HandRank.STRAIGHT_FLUSH;
        if (isFourOfAKind(ranks)) return HandRank.FOUR_OF_A_KIND;
        if (isFullHouse(ranks)) return HandRank.FULL_HOUSE;
        if (isFlush(suits)) return HandRank.FLUSH;
        if (isStraight(ranks)) return HandRank.STRAIGHT;
        if (isThreeOfAKind(ranks)) return HandRank.THREE_OF_A_KIND;
        if (isTwoPair(ranks)) return HandRank.TWO_PAIR;
        if (isOnePair(ranks)) return HandRank.ONE_PAIR;

        return HandRank.HIGH_CARD; // 기본적으로 탑카드
    }

    // 로열 플러시 판별
    private static boolean isRoyalFlush(List<Integer> ranks, List<Suit> suits) {
        return isStraightFlush(ranks, suits) && ranks.contains(10);
    }

    // 스트레이트 플러시 판별
    private static boolean isStraightFlush(List<Integer> ranks, List<Suit> suits) {
        return isStraight(ranks) && isFlush(suits);
    }

    // 포카드 판별
    private static boolean isFourOfAKind(List<Integer> ranks) {
        return hasNOfAKind(ranks, 4);
    }

    // 풀하우스 판별
    private static boolean isFullHouse(List<Integer> ranks) {
        return hasNOfAKind(ranks, 3) && hasNOfAKind(ranks, 2);
    }

    // 플러시 판별
    private static boolean isFlush(List<Suit> suits) {
        return new HashSet<>(suits).size() == 1;
    }

    // 스트레이트 판별
    private static boolean isStraight(List<Integer> ranks) {
        Set<Integer> rankSet = new HashSet<>(ranks);
        if (rankSet.size() != 5) return false; // 중복이 있으면 안 됨
        List<Integer> sortedRanks = new ArrayList<>(rankSet);
        Collections.sort(sortedRanks);
        return sortedRanks.get(4) - sortedRanks.get(0) == 4; // 연속적인 숫자
    }

    // 트리플 판별
    private static boolean isThreeOfAKind(List<Integer> ranks) {
        return hasNOfAKind(ranks, 3);
    }

    // 투페어 판별
    private static boolean isTwoPair(List<Integer> ranks) {
        Set<Integer> rankSet = new HashSet<>(ranks);
        return rankSet.size() == 3; // 두 개의 페어와 한 개의 카드를 가진 경우
    }

    // 원페어 판별
    private static boolean isOnePair(List<Integer> ranks) {
        return hasNOfAKind(ranks, 2);
    }

    // N개의 같은 숫자 카드가 있는지 확인
    private static boolean hasNOfAKind(List<Integer> ranks, int n) {
        Map<Integer, Long> rankCount = ranks.stream()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
        return rankCount.containsValue((long) n);
    }
}

