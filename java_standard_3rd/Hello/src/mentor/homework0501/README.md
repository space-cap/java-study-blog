
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




https://openjdk.org/jeps/441








