package mini_project.textrpg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SimpleTextRPG {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}

class Game {
    private Player player;
    private List<Location> locations;
    private Location currentLocation;
    private Scanner scanner;
    private boolean running;

    public Game() {
        scanner = new Scanner(System.in);
        initGame();
    }

    private void initGame() {
        // 플레이어 생성
        System.out.println("캐릭터 이름을 입력하세요:");
        String name = scanner.nextLine();
        player = new Player(name, 100, 10, 5);

        // 위치 생성
        locations = new ArrayList<>();
        Location town = new Location("마을", "평화로운 마을입니다.");
        Location forest = new Location("숲", "몬스터가 있는 숲입니다.");
        Location cave = new Location("동굴", "위험한 동굴입니다.");

        // 위치 연결
        town.addConnectedLocation(forest);
        forest.addConnectedLocation(town);
        forest.addConnectedLocation(cave);
        cave.addConnectedLocation(forest);

        // 몬스터 추가
        forest.addMonster(new Monster("늑대", 50, 7, 3, 20, 10));
        cave.addMonster(new Monster("곰", 100, 15, 8, 50, 30));
        cave.addMonster(new Monster("드래곤", 200, 25, 15, 100, 100));

        locations.add(town);
        locations.add(forest);
        locations.add(cave);

        currentLocation = town;
    }

    public void start() {
        running = true;
        System.out.println("텍스트 RPG 게임을 시작합니다!");
        System.out.println(player.getName() + "님, 모험을 시작하세요!");

        while (running) {
            showStatus();
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            processCommand(choice);

            if (!player.isAlive()) {
                System.out.println("게임 오버! " + player.getName() + "님이 사망했습니다.");
                running = false;
            }
        }
    }

    private void showStatus() {
        System.out.println("\n===== 상태 =====");
        System.out.println("위치: " + currentLocation.getName());
        System.out.println("체력: " + player.getHp() + "/" + player.getMaxHp());
        System.out.println("레벨: " + player.getLevel() + " (경험치: " + player.getExp() + "/" + player.getMaxExp() + ")");
        System.out.println("공격력: " + player.getAttack());
        System.out.println("방어력: " + player.getDefense());
        System.out.println("골드: " + player.getGold());
    }

    private void showMenu() {
        System.out.println("\n===== 메뉴 =====");
        System.out.println("1. 이동");
        System.out.println("2. 전투");
        System.out.println("3. 휴식 (체력 회복)");
        System.out.println("4. 종료");
        System.out.print("선택: ");
    }

    private void processCommand(int choice) {
        switch (choice) {
            case 1:
                move();
                break;
            case 2:
                battle();
                break;
            case 3:
                rest();
                break;
            case 4:
                running = false;
                System.out.println("게임을 종료합니다.");
                break;
            default:
                System.out.println("잘못된 입력입니다.");
        }
    }

    private void move() {
        System.out.println("\n===== 이동 =====");
        List<Location> connectedLocations = currentLocation.getConnectedLocations();

        if (connectedLocations.isEmpty()) {
            System.out.println("이동할 수 있는 장소가 없습니다.");
            return;
        }

        System.out.println("이동할 장소를 선택하세요:");
        for (int i = 0; i < connectedLocations.size(); i++) {
            System.out.println((i + 1) + ". " + connectedLocations.get(i).getName());
        }

        int locationChoice = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        if (locationChoice > 0 && locationChoice <= connectedLocations.size()) {
            currentLocation = connectedLocations.get(locationChoice - 1);
            System.out.println(currentLocation.getName() + "로 이동했습니다.");
            System.out.println(currentLocation.getDescription());
        } else {
            System.out.println("잘못된 선택입니다.");
        }
    }

    private void battle() {
        List<Monster> monsters = currentLocation.getMonsters();

        if (monsters.isEmpty()) {
            System.out.println("이 지역에는 전투할 몬스터가 없습니다.");
            return;
        }

        System.out.println("\n===== 전투 =====");
        System.out.println("전투할 몬스터를 선택하세요:");

        for (int i = 0; i < monsters.size(); i++) {
            Monster monster = monsters.get(i);
            System.out.println((i + 1) + ". " + monster.getName() + " (HP: " + monster.getHp() + ")");
        }

        int monsterChoice = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        if (monsterChoice > 0 && monsterChoice <= monsters.size()) {
            Monster target = monsters.get(monsterChoice - 1);
            combatLoop(target);
        } else {
            System.out.println("잘못된 선택입니다.");
        }
    }

    private void combatLoop(Monster monster) {
        System.out.println(monster.getName() + "와(과) 전투를 시작합니다!");

        while (player.isAlive() && monster.isAlive()) {
            // 플레이어 턴
            player.attack(monster);
            if (!monster.isAlive()) {
                System.out.println(monster.getName() + "을(를) 처치했습니다!");
                player.gainExp(monster.getDropExp());
                player.gainGold(monster.getDropGold());
                break;
            }

            // 몬스터 턴
            monster.attack(player);
            if (!player.isAlive()) {
                System.out.println("당신은 " + monster.getName() + "에게 패배했습니다.");
                break;
            }

            // 전투 상태 표시
            System.out.println("플레이어 HP: " + player.getHp() + "/" + player.getMaxHp());
            System.out.println(monster.getName() + " HP: " + monster.getHp());

            System.out.println("계속 싸우려면 엔터키를 누르세요...");
            scanner.nextLine();
        }
    }

    private void rest() {
        int restCost = 10;

        if (player.getGold() < restCost) {
            System.out.println("휴식을 취하기 위한 골드가 부족합니다. (필요: " + restCost + " 골드)");
            return;
        }

        player.setHp(player.getMaxHp());
        player.spendGold(restCost);
        System.out.println("휴식을 취했습니다. 체력이 완전히 회복되었습니다!");
    }
}

class Character {
    protected String name;
    protected int hp;
    protected int maxHp;
    protected int attack;
    protected int defense;

    public Character(String name, int maxHp, int attack, int defense) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attack = attack;
        this.defense = defense;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    // Getter/Setter 메소드들...
    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }

    public void setHp(int hp) {
        this.hp = Math.min(hp, maxHp);
    }
}

class Player extends Character {
    private int level;
    private int exp;
    private int maxExp;
    private int gold;

    public Player(String name, int maxHp, int attack, int defense) {
        super(name, maxHp, attack, defense);
        this.level = 1;
        this.exp = 0;
        this.maxExp = 100;
        this.gold = 50;
    }

    public void attack(Monster monster) {
        Random random = new Random();
        int damage = this.attack - monster.getDefense() + random.nextInt(5);

        if (damage < 1) damage = 1;

        monster.setHp(monster.getHp() - damage);
        System.out.println(this.name + "이(가) " + monster.getName() + "에게 " + damage + "의 피해를 입혔습니다.");
    }

    public void gainExp(int amount) {
        exp += amount;
        System.out.println(amount + "의 경험치를 획득했습니다.");

        while (exp >= maxExp) {
            levelUp();
        }
    }

    public void levelUp() {
        level++;
        exp -= maxExp;
        maxExp = level * 100;
        maxHp += 20;
        hp = maxHp;
        attack += 2;
        defense += 1;

        System.out.println("레벨 업! 현재 레벨: " + level);
    }

    public void gainGold(int amount) {
        gold += amount;
        System.out.println(amount + "골드를 획득했습니다.");
    }

    public void spendGold(int amount) {
        gold -= amount;
    }

    // Getter 메소드들...
    public int getLevel() { return level; }
    public int getExp() { return exp; }
    public int getMaxExp() { return maxExp; }
    public int getGold() { return gold; }
}

class Monster extends Character {
    private int dropExp;
    private int dropGold;

    public Monster(String name, int maxHp, int attack, int defense, int dropExp, int dropGold) {
        super(name, maxHp, attack, defense);
        this.dropExp = dropExp;
        this.dropGold = dropGold;
    }

    public void attack(Player player) {
        Random random = new Random();
        int damage = this.attack - player.getDefense() + random.nextInt(3);

        if (damage < 1) damage = 1;

        player.setHp(player.getHp() - damage);
        System.out.println(this.name + "이(가) " + player.getName() + "에게 " + damage + "의 피해를 입혔습니다.");
    }

    // Getter 메소드들...
    public int getDropExp() { return dropExp; }
    public int getDropGold() { return dropGold; }
}

class Location {
    private String name;
    private String description;
    private List<Monster> monsters;
    private List<Location> connectedLocations;

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        this.monsters = new ArrayList<>();
        this.connectedLocations = new ArrayList<>();
    }

    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

    public void addConnectedLocation(Location location) {
        connectedLocations.add(location);
    }

    // Getter 메소드들...
    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<Monster> getMonsters() { return monsters; }
    public List<Location> getConnectedLocations() { return connectedLocations; }
}
