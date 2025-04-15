package beginner.phone;

public class ApplePhone {
    String modelName;

    ApplePhone(String modelName) {
        this.modelName = modelName;
    }

    public String myString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

}
