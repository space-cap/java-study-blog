package mentor.homework0501;

import java.util.Optional;

enum ItemType {Weapon, Charm, Gold}

public class SwitchesAndNullTest {
    public static void main(String[] args) {
        System.out.println("Hello, Switches and Null");

        processItem(ItemType.Weapon);
        processItem(null);

        // Optional을 사용한 enum 처리
        processItemSafely(ItemType.Gold);
        processItemSafely(null);
    }

    public static void processItem(ItemType type) {
        String name = switch (type) {
            case Weapon -> "검";
            case Charm -> "부적";
            case Gold -> "금화";
            case null -> "알 수 없는 아이템";
        };

        System.out.println("아이템 종류: " + (type != null ? type : "없음") + ", 이름: " + name);
    }

    // Optional을 사용한 null 안전 처리 메서드
    public static void processItemSafely(ItemType type) {
        Optional<ItemType> optType = Optional.ofNullable(type);

        String name = optType.map(t -> {
            return switch (t) {
                case Weapon -> "마법 검";
                case Charm -> "행운의 부적";
                case Gold -> "반짝이는 금화";
            };
        }).orElse("아이템 없음");

        System.out.println("Optional 처리 - 아이템: " + name);
    }
}
