package lee;

import java.util.Random;

// 게임 캐릭터 클래스
class GameCharacter {
    private String type;
    private int level;
    private int health;
    private boolean hasWeapon;
    private String specialAbility;

    public GameCharacter(String type, int level, int health, boolean hasWeapon, String specialAbility) {
        this.type = type;
        this.level = level;
        this.health = health;
        this.hasWeapon = hasWeapon;
        this.specialAbility = specialAbility;
    }

    public String getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public boolean hasWeapon() {
        return hasWeapon;
    }

    public String getSpecialAbility() {
        return specialAbility;
    }

    @Override
    public String toString() {
        return "GameCharacter{" +
                "type='" + type + '\'' +
                ", level=" + level +
                ", health=" + health +
                ", hasWeapon=" + hasWeapon +
                ", specialAbility='" + specialAbility + '\'' +
                '}';
    }
}

// 아이템 열거형
enum Item {
    SWORD, SHIELD, POTION, MAP, KEY, GOLD_COIN
}

// 명령어 클래스
class Command {
    private String action;
    private String target;

    public Command(String action, String target) {
        this.action = action;
        this.target = target;
    }

    public String getAction() {
        return action;
    }

    public String getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return action + " " + target;
    }
}

public class GamePatternMatchingExample {
    public static void main(String[] args) {
        System.out.println("===== 게임 패턴 매칭 예제 =====");

        // 다양한 게임 객체 생성
        GameCharacter warrior = new GameCharacter("Warrior", 10, 100, true, "BerserkerRage");
        GameCharacter mage = new GameCharacter("Mage", 8, 70, false, "Fireball");
        GameCharacter rogue = new GameCharacter("Rogue", 7, 80, true, "Stealth");
        GameCharacter novice = new GameCharacter("Novice", 1, 50, false, null);

        // null 캐릭터 테스트용
        GameCharacter unknownCharacter = null;

        // 캐릭터 평가
        System.out.println("\n----- 캐릭터 평가 -----");
        evaluateCharacter(warrior);
        evaluateCharacter(mage);
        evaluateCharacter(rogue);
        evaluateCharacter(novice);
        evaluateCharacter(unknownCharacter);

        // 아이템 처리
        System.out.println("\n----- 아이템 처리 -----");
        processItem(Item.SWORD, warrior);
        processItem(Item.POTION, mage);
        processItem(Item.MAP, novice);
        processItem(Item.KEY, rogue);
        processItem(null, warrior);

        // 명령어 처리
        System.out.println("\n----- 명령어 처리 -----");
        processCommand(new Command("attack", "goblin"), warrior);
        processCommand(new Command("cast", "fireball"), mage);
        processCommand(new Command("hide", ""), rogue);
        processCommand(new Command("help", ""), novice);
        processCommand(null, warrior);

        // 복합 객체 처리
        System.out.println("\n----- 게임 이벤트 처리 -----");
        processGameEvent(warrior, Item.SWORD);
        processGameEvent(mage, Item.POTION);
        processGameEvent(rogue, null);
        processGameEvent(null, Item.GOLD_COIN);
    }

    // 캐릭터 평가 메서드 - 타입과 레벨에 따른 다양한 응답
    static void evaluateCharacter(GameCharacter character) {
        System.out.println("캐릭터 평가: " + (character != null ? character.getType() : "없음"));

        switch (character) {
            case null -> System.out.println("캐릭터가 선택되지 않았습니다. 먼저 캐릭터를 생성하세요.");

            // 상수 패턴 - 특정 타입 캐릭터 처리
            case GameCharacter c when c.getType().equals("Warrior") && c.getLevel() >= 10 ->
                    System.out.println("강력한 전사! 당신은 최전선에서 싸울 준비가 되었습니다.");

            case GameCharacter c when c.getType().equals("Mage") && c.getLevel() >= 10 ->
                    System.out.println("숙련된 마법사! 당신의 마법은 적에게 공포의 대상입니다.");

            case GameCharacter c when c.getType().equals("Rogue") && c.getLevel() >= 10 ->
                    System.out.println("그림자의 도적! 아무도 당신을 발견하지 못합니다.");

            // 레벨 구간에 따른 처리
            case GameCharacter c when c.getLevel() > 5 ->
                    System.out.println(c.getType() + "는 경험이 풍부합니다. 모험을 떠날 준비가 되었습니다.");

            case GameCharacter c when c.getLevel() >= 3 ->
                    System.out.println(c.getType() + "는 기초 훈련을 마쳤습니다. 마을 근처에서 사냥해보세요.");

            // 기본 케이스 - 초보자
            case GameCharacter c -> System.out.println(c.getType() + "는 아직 초보입니다. 더 많은 훈련이 필요합니다.");
        }
    }

    // 아이템 처리 메서드
    static void processItem(Item item, GameCharacter character) {
        System.out.println("아이템 처리: " + item + " for " +
                (character != null ? character.getType() : "알 수 없음"));

        switch (item) {
            case null -> System.out.println("아이템이 없습니다.");

            case Item i when i.name().equals("SWORD") && character != null && character.getType().equals("Warrior") -> {
                System.out.println("전사에게 완벽한 아이템입니다! 공격력/방어력 +40%");
            }

            case SWORD, SHIELD -> {
                if (character != null) {
                    if (character.getType().equals("Warrior")) {
                        // 전사 특화 처리
                        System.out.println("전사에게 완벽한 아이템입니다! 공격력/방어력 +20%");
                    } else {
                        // 일반 처리
                        System.out.println("전사에게 완벽한 아이템입니다! 공격력/방어력 +30%");
                    }
                }
            }



            case POTION -> System.out.println("체력 회복 포션을 인벤토리에 보관했습니다.");

            // 탐색 아이템
            case MAP -> System.out.println("새로운 지역이 지도에 표시되었습니다.");

            case KEY -> System.out.println("이 열쇠는 어딘가의 문을 열 것입니다.");

            // 기타 아이템
            case GOLD_COIN -> System.out.println("골드를 획득했습니다! 상점에서 사용할 수 있습니다.");

            // 열거형이므로 default가 필요 없음
        }
    }

    // 명령어 처리 메서드
    static void processCommand(Command command, GameCharacter character) {
        System.out.println("명령어 처리: " +
                (command != null ? command : "없음") + " by " +
                (character != null ? character.getType() : "알 수 없음"));

        switch (command) {
            case null -> System.out.println("명령어가 입력되지 않았습니다. 'help'를 입력하세요.");

            // 전투 관련 명령어
            case Command c when c.getAction().equals("attack") && character != null && character.hasWeapon() ->
                    System.out.println(character.getType() + "가 " + c.getTarget() + "을(를) 공격합니다. 치명타!");

            case Command c when c.getAction().equals("attack") && character != null ->
                    System.out.println(character.getType() + "가 " + c.getTarget() + "을(를) 공격합니다. (무기가 없어 데미지가 낮습니다)");

            // 마법 관련 명령어
            case Command c when c.getAction().equals("cast") && character != null && character.getType().equals("Mage") ->
                    System.out.println("마법사가 " + c.getTarget() + " 마법을 시전합니다. 강력한 효과!");

            case Command c when c.getAction().equals("cast") -> System.out.println("마법을 시전할 수 없습니다. 마법사 캐릭터가 필요합니다.");

            // 특수 능력 명령어
            case Command c when c.getAction().equals("hide") && character != null && character.getType().equals("Rogue") ->
                    System.out.println("도적이 그림자 속으로 숨었습니다. 적들이 당신을 볼 수 없습니다.");

            // 도움말 명령어
            case Command c when c.getAction().equals("help") ->
                    System.out.println("사용 가능한 명령어: attack, cast, hide, use, move, inventory");

            // 기본 케이스
            case Command c -> System.out.println("'" + c.getAction() + "'은(는) 알 수 없는 명령어입니다. 'help'를 입력하세요.");
        }
    }

    // 복합 객체 처리 - 캐릭터와 아이템의 조합에 따른 이벤트 처리
    static void processGameEvent(GameCharacter character, Item item) {
        System.out.println("게임 이벤트 처리: " +
                (character != null ? character.getType() : "알 수 없음") + " + " +
                item);

        switch (character) {
            //case null when item == null  -> System.out.println("게임 이벤트가 트리거되지 않았습니다.");
            case GameCharacter c when c == null && item == null -> System.out.println("게임 이벤트가 트리거되지 않았습니다.");

            case null -> System.out.println("아이템 " + item + "이(가) 바닥에 떨어져 있습니다.");

            case GameCharacter c when item == null -> System.out.println(c.getType() + "가 주변을 탐색하지만 아무것도 발견하지 못했습니다.");

            case GameCharacter c when item == Item.SWORD && c.getType().equals("Warrior") ->
                    System.out.println("전사가 전설의 검을 발견했습니다! 공격력이 크게 증가합니다.");

            case GameCharacter c when item == Item.POTION && c.getHealth() < 30 ->
                    System.out.println(c.getType() + "가 치유의 포션을 사용했습니다. 체력이 완전히 회복되었습니다!");

            case GameCharacter c when item == Item.GOLD_COIN && new Random().nextInt(10) == 0 ->
                    System.out.println("행운의 주인공! " + c.getType() + "가 금화를 주워 황금 상자를 발견했습니다!");

            case GameCharacter c -> System.out.println(c.getType() + "가 " + item + "을(를) 획득했습니다.");
        }
    }
}
