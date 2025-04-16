package ch12;

import java.util.ArrayList;




class Car {
    private String name;

    Car(String name){
        this.name = name;
    }

    public String toString() {
        return name;
    }
}

class FormulaCar extends Car {
    FormulaCar(String name) {
        super(name);
    }

    public String toString() {
        return "FormulaCar: " + super.toString();
    }
}

class TouringCar extends Car {
    TouringCar(String name) {
        super(name);
    }

    public String toString() {
        return "TouringCar: " + super.toString();
    }
}

class Truck extends Car {
    Truck(String name) {
        super(name);
    }

    public String toString() {
        return "Truck: " + super.toString();
    }
}

public class ArrayListTest {
    public static void main(String[] args) {
        System.out.println("ArrayListTest");
        ArrayList<Car> cars = new ArrayList<>(3);
        cars.add(new FormulaCar("FormulaCar1"));
        cars.add(new TouringCar("TouringCar1"));
        cars.add(new Truck("Truck1"));

        for (Car car : cars) {
            System.out.println(car);
        }

    }
}
