package mentor.homework0501;

class Item {
    String name;
    int price;
}

class Potion extends Item {
    String effect;
}

class Sword extends Item {
    int damage;
}

class Armor extends Item {
    int defense;

    @Override
    public String toString() {
        return "Armor";
    }
}

class Dungeon {
    public record Pair<T, U>(T first, U second) {}
    public Pair<Boolean, Item> Clear() {
        System.out.println("Dungeon is cleared.");
        return new Pair<>(true, new Armor());
    }
}

public class RecordTest {
    public static void main(String[] args) {
        System.out.println("Hello, Record!");

        Dungeon dungeon = new Dungeon();
        var result = dungeon.Clear();
        System.out.println(result.first());
        System.out.println(result.second());
    }
}
