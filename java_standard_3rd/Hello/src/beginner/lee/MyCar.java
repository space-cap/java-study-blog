package beginner.lee;

import beginner.casting.Ferrari;

import java.util.ArrayList;

class Car {
    void sound() {
        System.out.println("Car sound");
    }
}

class Perrari0422 extends Car {
    void sound() {
        System.out.println("Perrari sound");
    }
}

class MyCar0422 {
    ArrayList<Car> cars = new ArrayList<Car>();
    void addCar(Car car) {
        cars.add(car);
    }

}



public class MyCar {
    public static void main(String[] args){
        System.out.println("Hello, World!");

        Car ferrari = new Perrari0422();
        MyCar0422 myCar = new MyCar0422();
        myCar.addCar(ferrari);


    }
}
