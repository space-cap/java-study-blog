package mini_project.pokergame1;

import java.util.Comparator;

public class PokerHandComparator implements Comparator<Player> {

    @Override
    public int compare(Player p1, Player p2) {
        // 족보 순위 비교
        int rankCompare = Integer.compare(p2.getHandRank().ordinal(), p1.getHandRank().ordinal());
        if (rankCompare != 0) return rankCompare;

        // 족보가 같으면 강한 카드 순으로 비교
        for (int i = 0; i < p1.getHandStrength().size(); i++) {
            int strengthCompare = Integer.compare(p2.getHandStrength().get(i), p1.getHandStrength().get(i));
            if (strengthCompare != 0) return strengthCompare;
        }

        return 0; // 동점
    }
}

