package beginner.casting;

public class Person {
    Car myCar;

    public void buyCar(Car car) {
        this.myCar = car;
    }

    public void pressBbang() {
        myCar.bbangbbang();
    }
}
