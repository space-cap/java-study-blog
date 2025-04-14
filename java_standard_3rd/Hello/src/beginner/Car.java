package beginner;

public class Car {
    private int tires;
    private String color;

    Car(int tires, String color) {
        this.tires = tires;
        this.color = color;
    }

    public void sound(float f) {
        System.out.println("부웅부웅");
        System.out.println(this.color);
    }

    public void sound(Car this) {
        System.out.println("부웅부웅");
        //System.out.println(this.color);
    }

    public void sound(Car this, int tires) {
        System.out.println("부웅부웅");
        System.out.println(this.color);
    }

    public void sell(Car this) {
        System.out.println("차를 팝니다.");
        this.sound();
    }
}
