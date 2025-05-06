package pattern;

// 상태라는 설계도
interface DoorState {
    void openDoor(); // 문을 여는 행동
}

// 잠긴 상태
class LockedState implements DoorState {
    public void openDoor() {
        System.out.println("문이 잠겨 있어! 열쇠로 풀어야 해!");
    }
}

// 열린 상태
class UnlockedState implements DoorState {
    public void openDoor() {
        System.out.println("문이 열렸어! 들어와!");
    }
}

// 문 객체
class Door {
    private DoorState state; // 지금 문의 상태를 기억해

    public Door() {
        state = new LockedState(); // 처음엔 잠긴 상태야
    }

    public void setState(DoorState state) {
        this.state = state; // 상태를 바꿀 수 있어
    }

    public void open() {
        state.openDoor(); // 현재 상태에 따라 행동해
    }
}

// 테스트
public class StateEx1 {
    public static void main(String[] args) {
        Door door = new Door();
        door.open(); // 출력: 문이 잠겨 있어! 열쇠로 풀어야 해!

        door.setState(new UnlockedState()); // 상태를 열린 상태로 바꿔
        door.open(); // 출력: 문이 열렸어! 들어와!
    }
}