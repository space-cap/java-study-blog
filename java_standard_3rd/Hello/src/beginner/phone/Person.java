package beginner.phone;

public class Person {
    String name;
    ApplePhone applePhone;

    Person(String name) {
        this.name = name;
    }

    public void buy(ApplePhone applePhone) {
        this.applePhone = applePhone;
        System.out.println(name + "가 " + applePhone.modelName + "를 구매했습니다.");
    }

    public void sayJarang() {
        System.out.println("나 " + applePhone.modelName + " 폰 샀다!");
    }
}
