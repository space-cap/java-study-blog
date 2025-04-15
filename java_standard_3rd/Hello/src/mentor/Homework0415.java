package mentor;

import java.util.Random;
import java.util.Scanner;


class Car {
    private int moveNum;
    private final int MOVE_NUM = 1;

    Car() {
        this.moveNum = 1;
    }

    private void move() {
        moveNum += MOVE_NUM;
    }

    public void checkMove(int randomNum) {
        if(randomNum > 4) {
            move();
        }
    }

    public void display() {
        for(int i = 0; i < moveNum; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}



public class Homework0415 {
    public static void main(String[] args) {
        System.out.println("Homework0415");

        Scanner scanner = new Scanner(System.in);

        System.out.println("자동차 대수는 몇 대 인가요? ");
        String value = scanner.nextLine();

        System.out.println("시도할 회수는 몇 회 인가요? ");
        int number = scanner.nextInt();

        System.out.println("자동차 대수는 : " + value);
        System.out.println("시도할 회수는 : " + number);

        Car[] cars = new Car[Integer.parseInt(value)];
        for(int i=0;i<cars.length;i++) {
            cars[i] = new Car();
        }

        for (Car car : cars) {
            car.display();
        }
        System.out.println();

        Random random = new Random();
        for(int i=0;i<number;i++) {
            for (Car car : cars) {
                int randomNum = random.nextInt(10);
                car.checkMove(randomNum);
            }
            for (Car car : cars) {
                car.display();
            }
            System.out.println();
        }

    }
}
