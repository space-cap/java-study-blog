package ch06;

class Car {
    void showThis() {
        System.out.println(this);  // 현재 인스턴스의 주소 출력
    }
}

public class Ex6_5_4 {
    public static void main(String[] args) {
        Car car1 = new Car();
        Car car2 = new Car();

        car1.showThis();  // car1의 주소 출력
        car2.showThis();  // car2의 주소 출력
    }
}
