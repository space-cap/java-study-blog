
## 숙제
### 아래 디자인 패턴 간단하게 이해해오기
proxy pattern  
factory pattern  
template method pattern  
strategy pattern  

### Proxy Pattern(프록시 패턴)  
본인 작업을 위임.  
왜? 나도 모르겠다.  
검색에서는  
- **보안**: 아무나 진짜 객체를 못 쓰게 막을 수 있다.
- **성능**: 진짜 객체를 늦게 만들고 싶을 때 대신 만들어 준다.
- **접근 제어**: 사용할 수 있는 조건을 걸 수 있다.

그냥 본인에서 해도 될 것 같은데...


### Factory Pattern(팩토리 패턴)
**필요한 객체를 직접 만들지 않고, 공장에게 만들어 달라고 부탁하는 방법**
왜? 같은 인터페이스 상속받은 클래스를 한 곳에서 객체를 만들기 위해서
나중에 같은 인터페이스 상속받은 클래스 추가해도 공장만 살짝 바꾸면 되니깐 유지 보수가 쉽다.


```mermaid
classDiagram
    class Item {
        <<interface>>
        +use()
    }

    class HealingPotion {
        +use()
    }

    class ManaPotion {
        +use()
    }

    class AttackBoost {
        +use()
    }

    class ItemFactory {
        +createItem(type): Item
    }

    Item <|.. HealingPotion
    Item <|.. ManaPotion
    Item <|.. AttackBoost
```

```java
// 1. 아이템 인터페이스
interface Item {
    void use();
}

// 2. 실제 아이템 클래스들
class HealingPotion implements Item {
    public void use() {
        System.out.println("❤️ 체력을 회복합니다!");
    }
}

class ManaPotion implements Item {
    public void use() {
        System.out.println("🔵 마나를 회복합니다!");
    }
}

class AttackBoost implements Item {
    public void use() {
        System.out.println("💥 공격력이 잠시 증가합니다!");
    }
}

// 3. 아이템 공장 (Factory)
class ItemFactory {
    public static Item createItem(String type) {
        switch (type) {
            case "healing":
                return new HealingPotion();
            case "mana":
                return new ManaPotion();
            case "boost":
                return new AttackBoost();
            default:
                throw new IllegalArgumentException("❌ 알 수 없는 아이템 타입: " + type);
        }
    }
}

// 4. 사용 예 (게임 플레이 상황)
public class Game {
    public static void main(String[] args) {
        Item item1 = ItemFactory.createItem("healing");
        item1.use(); // ❤️ 체력 회복

        Item item2 = ItemFactory.createItem("mana");
        item2.use(); // 🔵 마나 회복

        Item item3 = ItemFactory.createItem("boost");
        item3.use(); // 💥 공격력 증가
    }
}
```


















