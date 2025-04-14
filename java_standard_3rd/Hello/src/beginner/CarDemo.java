package beginner;

public class CarDemo {
    public static void main(String[] args) {
        System.out.println("CarDemo");

        Car myCar = new Car(4, "red");
        System.out.println(myCar);
        myCar.sound();
        myCar.sell();
    }
}
