
## 숙제
### Java 17, 21 부터 추가된 아래 클래스, 개념에 대해서 공부해오기
record class  
sealed class  
Matching for Switch Pattern  
Switches and null  
Case refinement  

## 1. Record 클래스
**Record 클래스**는 Java 14에서 처음 도입되어 Java 16부터 정식으로 지원되는 새로운 형태의 클래스입니다.  
주 목적은 불변(immutable) 데이터 객체를 간단하고 명확하게 정의하는 것입니다.  

자세한 내용은 아래 링크 보세요.  
https://download.java.net/java/early_access/panama/docs/api/java.base/java/lang/Record.html  
https://docs.oracle.com/en/java/javase/21/language/records.html  

**Record의 주요 특징**

- 모든 필드는 자동으로 `private final`로 선언되어 불변성을 보장합니다.
- 생성자, getter, `equals()`, `hashCode()`, `toString()` 메서드가 자동으로 생성됩니다.
- 클래스 선언 시 `record` 키워드를 사용하며, 필드(컴포넌트)는 소괄호 안에 나열합니다.
- 클래스는 자동으로 `final`이 되어 상속이 불가능합니다. 하지만 인터페이스는 구현할 수 있습니다.
- Setter(값 변경 메서드)는 제공하지 않습니다.
- 컴팩트 생성자(Compact Constructor)를 통해 생성자 내부에서 값 검증 등 추가 로직을 작성할 수 있습니다.

**Record 클래스 사용 예시**

```java
public record Student(String name, int age) { }
```
이렇게 선언하면, 아래와 같은 코드가 자동으로 동작합니다:

```java
Student student = new Student("Kim", 20);
System.out.println(student.name()); // Kim
System.out.println(student.age());  // 20
System.out.println(student);        // Student[name=Kim, age=20]
```
getter는 `getName()`이 아니라 `name()`처럼 필드명과 동일한 메서드로 생성됩니다.

## 2. Sealed class
**Sealed class**는 특정 클래스나 인터페이스가 상속(extends) 또는 구현(implements)될 수 있는 하위 타입을 명시적으로 제한하는 기능입니다. 
이를 통해 상속 계층 구조를 더 명확하게 제어할 수 있으며, 의도치 않은 클래스가 슈퍼클래스를 상속하는 것을 방지할 수 있습니다. 
Java 15에서 프리뷰로 도입되어 Java 17에서 정식 기능이 되었습니다.

자세한 내용은 아래 링크 보세요.  
https://docs.oracle.com/en/java/javase/17/language/sealed-classes-and-interfaces.html

**주요 특징**

- `sealed` 키워드로 선언하며, `permits` 키워드를 이용해 상속/구현을 허용할 하위 클래스를 명시합니다.
- 지정된 클래스만 해당 sealed 클래스를 상속할 수 있습니다. 목록에 없는 클래스가 상속하려 하면 컴파일 에러가 발생합니다.
- 하위 클래스는 반드시 `final`, `sealed`, `non-sealed` 중 하나로 선언해야 합니다.
    - `final`: 더 이상 상속 불가
    - `sealed`: 다시 한 번 상속 제한 가능
    - `non-sealed`: 제한을 풀어 자유롭게 상속 가능하게 함.
- sealed 클래스를 상속하는 하위 클래스들은 같은 패키지 또는 모듈 내에 있어야 합니다.
- 추상 클래스로도 선언할 수 있습니다.

**예시 코드**
```java

// `Join`는 오직 `EquiJoin`, `NonEquiJoin`, `OuterJoin`만 상속할 수 있습니다.
sealed class Join permits EquiJoin, NonEquiJoin, OuterJoin { }

// `EquiJoin`은 더 이상 상속될 수 없다.
final class EquiJoin extends Join { }

// `NonEquiJoin`는 자유롭게 상속될 수 있다.
non-sealed class NonEquiJoin extends Join { }

// `OuterJoin`은 다시 한 번 상속 제한이 가능합니다.
sealed class OuterJoin extends Join permits LeftOuterJoin, RightOuterJoin { }

final class LeftOuterJoin extends OuterJoin { }
final class RightOuterJoin extends OuterJoin { }


public class SealedTest {
    public static void main(String[] args) {
        System.out.println("Hello, Sealed");
    }
}
```
Sealed class는 자바에서 상속 구조를 명확히 제한하고, 도메인 모델링과 코드의 안정성을 높이기 위한 기능입니다.

## 3. Pattern Matching for Switch
Pattern Matching for Switch는 Java 17부터 도입된 기능으로, switch문이나 switch 표현식에서 case 레이블에 타입 패턴을 사용할 수 있게 해주는 기능입니다. 
이로써 switch문의 활용 범위가 기존의 상수 값 비교에서 객체의 타입과 값 패턴까지 확장되어, 코드가 더 간결하고 읽기 쉬워집니다.

자세한 내용은 아래 링크에
https://openjdk.org/jeps/441
https://blogs.oracle.com/javamagazine/post/java-pattern-matching-switch-when-null

**간단한 예제**
```java
static void test(Object obj) {
    switch (obj) {
        case Character c -> {
            if (c.charValue() == 7) {
                System.out.println("Ding!");
            }
            System.out.println("Character, value " + c.charValue());
        }
        case Integer i -> System.out.println("Integer: " + i);
        default -> throw new IllegalStateException("Invalid argument");
    }
}
```
위 예제에서 `obj`의 타입에 따라 각 case가 실행됩니다. 
만약 `obj`가 `Character`라면 `c`로 바인딩되어 해당 블록이 실행되고, `Integer`라면 `i`로 바인딩됩니다.

**주요 특징**
- **패턴 케이스**: case 레이블에 타입 패턴(`case Type var`)을 사용할 수 있습니다.
- **패턴 변수의 범위**: 패턴 변수(`c`, `i` 등)는 해당 case 블록 내에서만 사용할 수 있습니다.
- **when 절**: Java 21부터는 `when` 절을 사용해 추가 조건을 붙일 수 있습니다.
  ```java
  case String s when s.length() > 5 -> System.out.println("Long string: " + s);
  ```
- **null 처리**: case 레이블에 `case null`을 명시적으로 쓸 수 있습니다.
- **switch 표현식/문 모두 지원**: 기존 switch문과 switch 표현식 모두에서 패턴 매칭을 사용할 수 있습니다.

**게임아이템 예제**
```java
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
```

실행결과
```text
Hello, Pattern Matching for Switch
전설의 무기 획득: 불의 검 (공격력: 120)
무기 획득: 짧은 칼 (공격력: 20)
치유 포션 획득: 강력한 치유 포션
포션 획득: 투명화 포션 (효과: invisible)
대량의 골드 획득! 5000 Gold
골드 획득: 50
아이템이 없습니다.

Process finished with exit code 0
```

**장점**
- 다양한 타입의 객체를 안전하고 명확하게 분기 처리할 수 있습니다.
- instanceof와 타입 캐스팅을 반복적으로 사용할 필요가 없어집니다.
- 코드의 가독성과 유지보수성이 크게 향상됩니다.


## 4. Switches and null
**전통적인 Java switch문**에서는 switch의 조건(피연산자)이 null일 경우 `NullPointerException`(NPE)이 발생합니다.

https://openjdk.org/jeps/441 ( Switches and null 로 검색 )
https://blogs.oracle.com/javamagazine/post/java-switch-expression-arrow-case-null
https://blogs.oracle.com/javamagazine/post/java-switch-statements-expressions


- **삼항 연산자 등으로 null을 다른 값으로 치환**
```java
static void testFooBarOld(String str) {
    switch (str == null ? "NULL" : str) {
        case "one": ...
        case "NULL": ...
    }
}
```
- **String.valueOf 등으로 null을 문자열로 변환**
```java
static void testFooBarOld(String str) {
    switch (String.valueOf(str)) {
        case "null": ...
    }
}
```

```java
// Prior to Java 21
static void testFooBarOld(String s) {
    if (s == null) {
        System.out.println("Oops!");
        return;
    }
    switch (s) {
        case "Foo", "Bar" -> System.out.println("Great");
        default           -> System.out.println("Ok");
    }
}
```

- Java 21부터는 switch문에서 `case null`을 명시적으로 사용할 수 있습니다.  
즉, switch문의 피연산자가 null이어도 NPE가 발생하지 않고, `case null`이 있으면 해당 블록이 실행됩니다.  
```java
// As of Java 21
static void testFooBarNew(String s) {
    switch (s) {
        case null         -> System.out.println("Oops");
        case "Foo", "Bar" -> System.out.println("Great");
        default           -> System.out.println("Ok");
    }
}
```

## 정리
- **Java 20 이하**: switch문의 피연산자가 null이면 NPE가 발생하므로, 반드시 switch문 진입 전에 null 체크가 필요합니다.
- **Java 21 이상**: switch문에서 `case null`을 사용할 수 있어, null도 안전하게 분기 처리할 수 있습니다.


## 5. Case refinement

https://openjdk.org/jeps/441 ( Case refinement 로 검색 )

- **when 절**: Java 21부터는 `when` 절을 사용해 추가 조건을 붙일 수 있습니다.
  ```java
  case String s when s.length() > 5 -> System.out.println("Long string: " + s);
  ```

























