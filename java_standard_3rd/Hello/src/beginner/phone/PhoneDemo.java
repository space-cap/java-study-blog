package beginner.phone;

public class PhoneDemo { // 객체 지향 세상
    public static void main(String[] args) {
        Person songa = new Person("손가");
        ApplePhone iPhone12mini = new ApplePhone("iPhone12mini");

        songa.buy(iPhone12mini);
        songa.sayJarang();

        System.out.println(iPhone12mini);
        System.out.println(iPhone12mini.myString());

    }
}
