package ch07;

class Robot718 {}

class DanceRobot718 extends Robot718 {
    void dance() {
        System.out.println("Dance");
    }
}

class SingRobot718 extends Robot718 {
    void sing() {
        System.out.println("Sing");
    }
}

class DrawRobot718 extends Robot718 {
    void draw() {
        System.out.println("Draw");
    }
}

public class Excrcise7_18 {
    public static void main(String[] args) {
        System.out.println("main hello");

        Robot718[] arr = {
            new DanceRobot718(),
            new SingRobot718(),
            new DrawRobot718()
        };

        for (int i = 0; i < arr.length; i++) {
            action(arr[i]);
        }
    }

    static void action(Robot718 robot) {
        if(robot instanceof DanceRobot718) {
            DanceRobot718 danceRobot = (DanceRobot718) robot;
            danceRobot.dance();
        } else if(robot instanceof SingRobot718) {
            SingRobot718 singRobot = (SingRobot718) robot;
            singRobot.sing();
        } else if(robot instanceof DrawRobot718) {
            DrawRobot718 drawRobot = (DrawRobot718) robot;
            drawRobot.draw();
        }
    }
}
