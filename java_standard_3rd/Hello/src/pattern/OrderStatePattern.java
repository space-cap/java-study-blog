package pattern;

// 상태 설계도: 모든 상태가 따라야 할 행동을 정해
interface OrderState {
    void cancelOrder(); // 주문 취소 행동
    void trackShipping(); // 배송 조회 행동
}

// 결제 대기 중 상태
class PendingPaymentState implements OrderState {
    public void cancelOrder() {
        System.out.println("주문이 취소됐어! 아직 결제 전이라 바로 취소 가능!");
    }

    public void trackShipping() {
        System.out.println("아직 배송 시작 안 했어! 결제 먼저 해줘!");
    }
}

// 배송 중 상태
class ShippedState implements OrderState {
    public void cancelOrder() {
        System.out.println("이미 배송 중이라 주문 취소 못해!");
    }

    public void trackShipping() {
        System.out.println("배송 중이야! 물건은 지금 트럭에 있어!");
    }
}

// 배송 완료 상태
class DeliveredState implements OrderState {
    public void cancelOrder() {
        System.out.println("물건이 도착했으니 취소 대신 환불 요청을 해줘!");
    }

    public void trackShipping() {
        System.out.println("이미 배송 완료됐어! 물건 잘 받았지?");
    }
}

// 주문 객체
class Order {
    private OrderState state; // 현재 주문 상태를 기억해

    public Order() {
        state = new PendingPaymentState(); // 처음엔 결제 대기 중 상태야
    }

    // 상태를 바꾸는 메서드
    public void setState(OrderState state) {
        this.state = state;
        System.out.println("주문 상태가 바뀌었어!");
    }

    // 주문 취소 요청
    public void cancel() {
        state.cancelOrder();
    }

    // 배송 조회 요청
    public void track() {
        state.trackShipping();
    }
}

// 테스트 코드
public class OrderStatePattern {
    public static void main(String[] args) {
        Order order = new Order();

        // 결제 대기 중 상태에서 행동
        order.cancel(); // 출력: 주문이 취소됐어! 아직 결제 전이라 바로 취소 가능!
        order.track();  // 출력: 아직 배송 시작 안 했어! 결제 먼저 해줘!

        // 배송 중 상태로 변경
        order.setState(new ShippedState());
        order.cancel(); // 출력: 이미 배송 중이라 주문 취소 못해!
        order.track();  // 출력: 배송 중이야! 물건은 지금 트럭에 있어!

        // 배송 완료 상태로 변경
        order.setState(new DeliveredState());
        order.cancel(); // 출력: 물건이 도착했으니 취소 대신 환불 요청을 해줘!
        order.track();  // 출력: 이미 배송 완료됐어! 물건 잘 받았지?
    }
}
