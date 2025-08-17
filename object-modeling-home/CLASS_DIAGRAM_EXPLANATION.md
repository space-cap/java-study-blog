# 집의 방 객체 모델링 - 클래스 다이어그램 설명

## 프로젝트 개요
JDK 21을 사용하여 집의 방을 객체 모델링한 프로젝트입니다. OOP 4대 원칙(캡슐화, 상속, 추상화, 다형성)을 모두 적용하여 설계되었습니다.

## 클래스 다이어그램 구조

### 1. 클래스 계층 구조
```
Air (최상위 클래스)
│
└── Room (추상 클래스)
    ├── LivingRoom (구체 클래스)
    ├── Bedroom (구체 클래스)
    ├── Kitchen (구체 클래스)
    └── Bathroom (구체 클래스)
```

### 2. 인터페이스 구조
```
인터페이스:
├── Illuminatable (조명 기능)
├── Heatable (난방 기능)
└── Ventilatable (환기 기능)
```

## OOP 4대 원칙 적용

### 1. 캡슐화 (Encapsulation)
- **private 필드**: 모든 클래스의 속성은 private으로 선언
- **public getter/setter**: 외부 접근을 위한 메서드 제공
- **데이터 검증**: setter에서 유효한 값만 허용

**예시:**
```java
// Air 클래스에서
private double temperature;
private double humidity;

public void setTemperature(double temperature) {
    if (temperature >= -50 && temperature <= 60) {
        this.temperature = temperature;
    }
}
```

### 2. 상속 (Inheritance)
- **Air → Room**: Room이 Air를 상속받아 공기의 속성을 가짐
- **Room → 구체적 방들**: 각 방 클래스가 Room의 기본 기능을 상속
- **protected 메서드**: 자식 클래스에서 접근 가능한 메서드 제공

**계층 관계:**
- `Air` (최상위): 온도, 습도, 산소농도, 청정도
- `Room` (중간): 방 이름, 면적, 창문 수, 전기 여부
- 구체적 방들: 각자의 고유 속성과 기능

### 3. 추상화 (Abstraction)
**추상 클래스 (Room):**
```java
public abstract class Room extends Air {
    public abstract void performMainFunction();
    public abstract String getRoomType();
}
```

**인터페이스:**
- `Illuminatable`: 조명 관련 기능 추상화
- `Heatable`: 난방 관련 기능 추상화  
- `Ventilatable`: 환기 관련 기능 추상화

### 4. 다형성 (Polymorphism)
**메서드 오버라이딩:**
- 각 방 클래스가 `performMainFunction()` 메서드를 다르게 구현
- 인터페이스 메서드들을 각 클래스의 특성에 맞게 구현

**인터페이스 다형성:**
```java
// 같은 인터페이스를 다른 클래스들이 구현
if (room instanceof Illuminatable illuminatable) {
    illuminatable.turnOnLight(); // 다형성 활용
}
```

## 클래스별 상세 설명

### Air (최상위 클래스)
- **역할**: 모든 공간에 존재하는 공기를 표현
- **주요 속성**: 온도, 습도, 산소농도, 청정도
- **주요 메서드**: `circulate()`, `purify()`

### Room (추상 클래스)
- **역할**: 모든 방의 공통 기능과 구조 정의
- **주요 속성**: 이름, 면적, 창문 수, 전기 여부
- **추상 메서드**: `performMainFunction()`, `getRoomType()`

### LivingRoom (거실)
- **구현 인터페이스**: Illuminatable, Heatable, Ventilatable
- **고유 기능**: TV 시청, 소파 사용
- **특징**: 모든 편의 기능을 가진 중심 공간

### Bedroom (침실)
- **구현 인터페이스**: Illuminatable, Heatable
- **고유 기능**: 수면, 옷장 정리
- **특징**: 조용하고 편안한 환경 조성

### Kitchen (주방)
- **구현 인터페이스**: Illuminatable, Ventilatable
- **고유 기능**: 요리, 설거지, 냉장고 사용
- **특징**: 밝은 조명과 강력한 환기 필요

### Bathroom (욕실)
- **구현 인터페이스**: Illuminatable, Heatable, Ventilatable
- **고유 기능**: 샤워, 목욕, 개인 위생
- **특징**: 습도 관리와 환기가 중요

## 설계 장점

### 1. 확장성
- 새로운 방 타입 추가 시 Room 클래스만 상속하면 됨
- 새로운 기능 인터페이스 추가 가능

### 2. 재사용성
- 공통 기능은 상위 클래스에서 재사용
- 인터페이스를 통한 기능별 그룹화

### 3. 유지보수성
- 각 클래스의 책임이 명확히 분리
- 변경 시 영향범위가 제한적

### 4. 다형성 활용
- 인터페이스를 통한 유연한 객체 처리
- 런타임에 객체 타입에 따른 동적 메서드 호출

## JDK 21 기능 활용

### Pattern Matching for Switch
```java
String feature = switch (room) {
    case LivingRoom lr -> "가족 휴식 공간 - TV: " + lr.hasTv();
    case Bedroom br -> "수면 공간 - 침대 수: " + br.getNumberOfBeds();
    // ...
};
```

### Virtual Threads (시뮬레이션)
```java
rooms.parallelStream().forEach(room -> {
    // 각 방의 병렬 처리
});
```

## 실행 방법
```bash
javac -d . src/main/java/com/house/modeling/demo/HouseModelingDemo.java
java com.house.modeling.demo.HouseModelingDemo
```

이 설계는 실제 집의 구조를 객체지향적으로 모델링하여 OOP의 핵심 개념들을 실습할 수 있도록 구성되었습니다.