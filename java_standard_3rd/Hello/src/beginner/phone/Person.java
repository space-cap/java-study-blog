package beginner.phone;

public class Person {
    String name;
    ApplePhone applePhone;
    SamsungPhone samsungPhone;

    Person(String name) {
        this.name = name;
    }

    public void buy(ApplePhone applePhone) {
        this.applePhone = applePhone;
        System.out.println(name + "가 " + applePhone.modelName + "를 구매했습니다.");
    }

    public void buy(SamsungPhone galaxyS24) {
        this.samsungPhone = galaxyS24;
        System.out.println(name + "가 " + samsungPhone.modelName + "를 구매했습니다.");
    }

    public void sayJarang() {
        System.out.println("나 " + applePhone.modelName + " 폰 샀다!");
    }

    public void turnOnPhone() {
        System.out.println(name + "가 " + applePhone.modelName + "를 켭습니다.");
        applePhone.turnOn();
    }

    public void turnOffPhone() {
        System.out.println(name + "(이)가 폰을 껐습니다.");
        applePhone.turnOff();
    }



    public void sayGalaxyJarang() {
        System.out.println("나 " + samsungPhone.modelName + " 폰 샀다!");
    }
}
