
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








