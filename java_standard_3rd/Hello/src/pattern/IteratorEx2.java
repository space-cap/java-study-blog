package pattern;

// 아이템 클래스
class Item {
    private String name;
    public Item(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}

// 인벤토리(집합 객체)
class Inventory {
    private Item[] items;
    public Inventory(Item[] items) {
        this.items = items;
    }
    public Iterator<Item> iterator() {
        return new InventoryIterator(items);
    }
}

// Iterator 인터페이스
interface Iterator<T> {
    boolean hasNext();
    T next();
}

// 인벤토리용 Iterator 구현체
class InventoryIterator implements Iterator<Item> {
    private Item[] items;
    private int position = 0;

    public InventoryIterator(Item[] items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        return position < items.length;
    }

    @Override
    public Item next() {
        return items[position++];
    }
}

// 사용 예시 (메인)
public class IteratorEx2 {
    public static void main(String[] args) {
        Item[] items = {
                new Item("포션"),
                new Item("검"),
                new Item("방패"),
                new Item("열쇠")
        };
        Inventory inventory = new Inventory(items);
        Iterator<Item> iterator = inventory.iterator();

        while (iterator.hasNext()) {
            Item item = iterator.next();
            System.out.println("아이템: " + item.getName());
        }
    }
}

