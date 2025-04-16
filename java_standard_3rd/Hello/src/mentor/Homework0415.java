package mentor;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


class Car {
    private int moveNum;
    private static final int MOVE_NUM = 1; // 이동할 때마다 증가하는 수
    private static final int MIN_RANDOM_TO_MOVE = 4; // 4 이상일 때 이동
    private static final int DEFAULT_MOVE_NUM = 1; // 초기값
    private Driver driver;

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

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public int getMoveNum() {
        return moveNum;
    }

    public String getDriverName() {
        return driver.getName();
    }
}

class Driver {
    private String name;

    Driver(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class FormulaCar extends Car {
    FormulaCar(Driver driver) {
        setDriver(driver);
    }
}

class TouringCar extends Car {
    TouringCar(Driver driver) {
        setDriver(driver);
    }
}

class Truck extends Car {
    Truck(Driver driver) {
        setDriver(driver);
    }
}

class RaceTrack {
    private final int carNum;
    private final int number;
    private ArrayList<Car> cars;
    private static final int RANDOM_BOUND = 10;

    RaceTrack(int carNum, int number) {
        this.carNum = carNum;
        this.number = number;

        initCars();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    private void initCars() {
        cars = new ArrayList<>(carNum);
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

    public void printResult() {
        System.out.println("경기 결과");
        int maxMoveNum = 0;
        for (Car car : cars) {
            if(car.getMoveNum() > maxMoveNum) {
                maxMoveNum = car.getMoveNum();
            }
        }

        if(maxMoveNum > 0) {
            for(Car car : cars) {
                if(car.getMoveNum() == maxMoveNum) {
                    System.out.println("우승자는 " + car.getDriverName() + " 입니다.");
                }
            }
            System.out.println("이동 거리: " + maxMoveNum);
        }
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
        raceTrack.addCar(new FormulaCar(new Driver("Driver1")));
        raceTrack.addCar(new TouringCar(new Driver("Driver2")));
        raceTrack.addCar(new Truck(new Driver("Driver3")));

        raceTrack.startRace();
        raceTrack.printResult();
    }
}
