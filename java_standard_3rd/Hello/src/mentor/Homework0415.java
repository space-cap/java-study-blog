package mentor;

import java.util.Random;
import java.util.Scanner;


class Car {
    private int moveNum;
    private static final int MOVE_NUM = 1; // 이동할 때마다 증가하는 수
    private static final int MIN_RANDOM_TO_MOVE = 4; // 4 이상일 때 이동
    private static final int DEFAULT_MOVE_NUM = 1; // 초기값

    Car() {
        this.moveNum = DEFAULT_MOVE_NUM;
    }

    public void move(int randomNum) {
        if(checkMove(randomNum)) {
            moveNum += MOVE_NUM;
        }
    }

    private boolean checkMove(int randomNum) {
        return randomNum > MIN_RANDOM_TO_MOVE;
    }

    public void print() {
        for(int i = 0; i < moveNum; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}

class RaceTrack {
    private final int carNum;
    private final int number;
    private Car[] cars;
    private static final int RANDOM_BOUND = 10;

    RaceTrack(int carNum, int number) {
        this.carNum = carNum;
        this.number = number;

        initCars();
    }

    private void initCars() {
        cars = new Car[carNum];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car();
        }
    }

    public void startRace() {
        print();

        Random random = new Random();
        for (int i = 0; i < number; i++) {
            for (Car car : cars) {
                int randomNum = random.nextInt(RANDOM_BOUND);
                car.move(randomNum);
            }
            print();
        }
    }

    private void print() {
        for (Car car : cars) {
            car.print();
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

        RaceTrack raceTrack = new RaceTrack(Integer.parseInt(value), number);
        raceTrack.startRace();
    }
}
