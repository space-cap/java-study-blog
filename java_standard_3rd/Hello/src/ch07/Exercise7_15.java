package ch07;

class Unit715 {}
class AirUnit715 extends Unit715 {}
class GroundUnit715 extends Unit715 {}
class Tank715 extends GroundUnit715 {}
class AirCraft715 extends AirUnit715 {}

public class Exercise7_15 {
    public static void main(String[] args) {
        System.out.println("main hello");

        Unit715 u = new GroundUnit715();
        Tank715 t = new Tank715();
        AirCraft715 ac = new AirCraft715();

        //u = (Unit715) ac; // Upcasting
        //u = ac; // Upcasting
        //GroundUnit715 gu = (GroundUnit715) u; // Downcasting
        //AirUnit715 au = ac; // Upcasting
        //t = (Tank715) u; // Downcasting
          GroundUnit715 gu2 = t; // Upcasting



    }
}
