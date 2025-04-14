package ch07;

abstract class Unit717 {
    int x, y;
    abstract void move(int x, int y);
    void stop() {}
}

class Marine717 extends Unit717 {
    void move(int x, int y) {}
    void stimPack() { /* code to use stimPack */ }
}

class Tank717 extends Unit717 {
    void move(int x, int y) {}
    void changeMode() { /* code to change mode */ }
}

class Dropship717 extends Unit717 {
    void move(int x, int y) {}
    void load() { /* code to load */ }
    void unload() { /* code to unload */ }
}





public class Exercise7_17 {
    public static void main(String[] args) {
        System.out.println("main hello");
    }
}
