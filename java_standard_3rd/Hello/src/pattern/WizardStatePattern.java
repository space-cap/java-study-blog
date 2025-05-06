package pattern;

// 상태 설계도: 모든 상태가 따라야 할 행동을 정해
interface WizardState {
    void castSpell(); // 마법을 사용하는 행동
}

// 정상 상태: 마법사가 건강할 때
class NormalState implements WizardState {
    public void castSpell() {
        System.out.println("파이어볼 발사! 쾅!");
    }
}

// 기절 상태: 마법사가 기절했을 때
class StunnedState implements WizardState {
    public void castSpell() {
        System.out.println("으악, 기절했어! 마법을 못 써!");
    }
}

// 마법사 캐릭터
class Wizard {
    private WizardState state; // 마법사의 현재 상태를 기억해

    public Wizard() {
        state = new NormalState(); // 처음엔 정상 상태야
    }

    // 상태를 바꾸는 메서드
    public void setState(WizardState state) {
        this.state = state;
        System.out.println("상태가 바뀌었어!");
    }

    // 마법을 사용하는 메서드
    public void castSpell() {
        state.castSpell(); // 현재 상태에 따라 마법을 써
    }
}

// 테스트 코드
public class WizardStatePattern {
    public static void main(String[] args) {
        Wizard wizard = new Wizard();

        // 정상 상태에서 마법 사용
        wizard.castSpell(); // 출력: 파이어볼 발사! 쾅!

        // 기절 상태로 바꾸고 마법 사용
        wizard.setState(new StunnedState());
        wizard.castSpell(); // 출력: 으악, 기절했어! 마법을 못 써!

        // 다시 정상 상태로 바꾸고 마법 사용
        wizard.setState(new NormalState());
        wizard.castSpell(); // 출력: 파이어볼 발사! 쾅!
    }
}

