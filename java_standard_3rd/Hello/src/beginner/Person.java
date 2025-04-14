package beginner;

public class Person {
    private String name;
    private int tall;
    private String phoneNumber;

    Person() {

    }

    Person(String name, int tall, String phoneNumber) {
        this.name = name;
        this.tall = tall;
        this.phoneNumber = phoneNumber;
        Person p = this;
    }

    Person(Person other) {
        this.name = other.name;
    }

    Person(String name, int tall) {
        this.name = name;
        this.tall = tall;
    }

    public void walk() {
        System.out.println("뚜벅뚜벅");
    }

    public void eat(String foodName) {
        System.out.println(foodName + " 냠냠");
    }
}



