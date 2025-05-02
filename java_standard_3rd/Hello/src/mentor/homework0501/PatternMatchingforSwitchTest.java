package mentor.homework0501;

sealed interface GameItem permits Weapon, Charm, Gold {
}

record Weapon(String name, int damage) implements GameItem {
}

record Charm(String name, String effect) implements GameItem {
}

record Gold(int amount) implements GameItem {
}

class GameItemHandler {
    public void handleItem(GameItem item) {
        switch (item) {
            case Weapon w when w.damage() >= 100 ->
                    System.out.println("전설의 무기 획득: " + w.name() + " (공격력: " + w.damage() + ")");
            case Weapon w -> System.out.println("무기 획득: " + w.name() + " (공격력: " + w.damage() + ")");
            case Charm p when p.effect().equals("heal") -> System.out.println("치유 포션 획득: " + p.name());
            case Charm p -> System.out.println("포션 획득: " + p.name() + " (효과: " + p.effect() + ")");
            case Gold g when g.amount() >= 1000 -> System.out.println("대량의 골드 획득! " + g.amount() + " Gold");
            case Gold g -> System.out.println("골드 획득: " + g.amount());
            case null -> System.out.println("아이템이 없습니다.");
            default -> System.out.println("알 수 없는 아이템입니다.");
        }
    }
}

public class PatternMatchingforSwitchTest {
    public static void main(String[] args) {
        System.out.println("Hello, Pattern Matching for Switch");

        GameItemHandler handler = new GameItemHandler();
        handler.handleItem(new Weapon("불의 검", 120));
        handler.handleItem(new Weapon("짧은 칼", 20));
        handler.handleItem(new Charm("강력한 치유 포션", "heal"));
        handler.handleItem(new Charm("투명화 포션", "invisible"));
        handler.handleItem(new Gold(5000));
        handler.handleItem(new Gold(50));
        handler.handleItem(null);
    }
}
