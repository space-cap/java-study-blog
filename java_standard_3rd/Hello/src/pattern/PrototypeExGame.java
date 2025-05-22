package pattern;

import java.util.HashMap;
import java.util.Map;

// 추상 몬스터 클래스 (프로토타입)
abstract class Monster implements Cloneable {
    protected String name;
    protected int hp;
    protected int attack;

    public Monster(String name, int hp, int attack) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
    }

    public abstract void display();

    @Override
    public Monster clone() {
        try {
            return (Monster) super.clone();  // 얕은 복사
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning failed");
        }
    }
}

// 구체적인 몬스터 클래스 1
class Goblin extends Monster {
    public Goblin() {
        super("Goblin", 30, 5);
    }

    @Override
    public void display() {
        System.out.println("👹 Goblin - HP: " + hp + ", ATK: " + attack);
    }
}

// 구체적인 몬스터 클래스 2
class Dragon extends Monster {
    public Dragon() {
        super("Dragon", 300, 50);
    }

    @Override
    public void display() {
        System.out.println("🐉 Dragon - HP: " + hp + ", ATK: " + attack);
    }
}

// 몬스터 등록소 (원본을 저장하고, 필요할 때 복제)
class MonsterRegistry {
    private Map<String, Monster> monsterMap = new HashMap<>();

    public void register(String type, Monster monster) {
        monsterMap.put(type, monster);
    }

    public Monster create(String type) {
        Monster prototype = monsterMap.get(type);
        return prototype != null ? prototype.clone() : null;
    }
}

public class PrototypeExGame {
    public static void main(String[] args) {
        System.out.println("hello prototype");

        MonsterRegistry registry = new MonsterRegistry();

        // 원본 등록
        registry.register("goblin", new Goblin());
        registry.register("dragon", new Dragon());

        // 몬스터 복제
        Monster goblin1 = registry.create("goblin");
        Monster goblin2 = registry.create("goblin");
        Monster dragon1 = registry.create("dragon");

        // 출력
        goblin1.display();  // 👹 Goblin - HP: 30, ATK: 5
        goblin2.display();  // 👹 Goblin - HP: 30, ATK: 5
        dragon1.display();  // 🐉 Dragon - HP: 300, ATK: 50

    }
}
