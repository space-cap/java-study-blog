package pattern;

interface GameState {
    void handle(PokerGame game); // 현재 상태에서 할 일
}

class DealCardsState implements GameState {
    public void handle(PokerGame game) {
        System.out.println("모든 플레이어에게 카드 5장씩 나눠줍니다!");
        // 실제로는 카드 덱에서 플레이어들에게 카드를 나눠주는 로직
        game.setState(new BettingState()); // 다음은 배팅 상태로
    }
}

class BettingState implements GameState {
    public void handle(PokerGame game) {
        System.out.println("플레이어들이 배팅하거나 폴드합니다!");
        // 배팅 로직: 플레이어가 돈을 걸거나 포기
        game.setState(new DrawCardsState()); // 다음은 카드 교체 상태로
    }
}

class DrawCardsState implements GameState {
    public void handle(PokerGame game) {
        System.out.println("플레이어가 원하는 카드를 교체합니다!");
        // 카드 교체 로직
        game.setState(new ShowdownState()); // 다음은 결과 확인 상태로
    }
}


class ShowdownState implements GameState {
    public void handle(PokerGame game) {
        System.out.println("카드를 공개하고 승자를 가립니다!");
        // 승자 결정 로직
        game.setState(new GameOverState()); // 게임 종료
    }
}


class GameOverState implements GameState {
    public void handle(PokerGame game) {
        System.out.println("게임 끝! 상금을 나눠줍니다!");
        // 상금 분배 로직
    }
}


class PokerGame {
    private GameState state;

    public PokerGame() {
        state = new DealCardsState(); // 게임은 카드 나눠주기로 시작
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public void nextStep() {
        state.handle(this); // 현재 상태의 행동을 실행
    }
}






public class StatePockerEx1 {
    public static void main(String[] args) {
        PokerGame game = new PokerGame();

        game.nextStep(); // "모든 플레이어에게 카드 5장씩 나눠줍니다!"
        game.nextStep(); // "플레이어들이 배팅하거나 폴드합니다!"
        game.nextStep(); // "플레이어가 원하는 카드를 교체합니다!"
        game.nextStep(); // "카드를 공개하고 승자를 가립니다!"
        game.nextStep(); // "게임 끝! 상금을 나눠줍니다!"
    }
}
