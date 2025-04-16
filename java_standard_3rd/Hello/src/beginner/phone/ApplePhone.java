package beginner.phone;

public class ApplePhone {
    String modelName;

    ApplePhone(String modelName) {
        this.modelName = modelName;
    }

    public String myString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    public void turnOn() {
        System.out.println("@@@ 애플 로고 @@@");
    }

    public void turnOff() {
        System.out.println("띠로리로");
    }
}

