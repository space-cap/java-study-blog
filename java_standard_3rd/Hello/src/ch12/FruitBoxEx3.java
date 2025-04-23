package ch12;

import java.util.ArrayList;

class Fruit {
    public String toString() {
        return "Fruit";
    }
}

class Apple extends Fruit {
    public String toString() {
        return "Apple";
    }
}

class Grape extends Fruit {
    public String toString() {
        return "Grape";
    }
}

class Juice {
    String name;
    Juice(String name) {
        this.name = name + " juice";
    }

    public String toString() {
        return name;
    }
}

class Juicer {
    static Juice makeJuice(FruitBox<? extends Fruit> box) {
        String tmp = "";
        for (Fruit f : box.getList()) {
            tmp += f + ", ";
        }
        return new Juice(tmp);
    }
}

public class FruitBoxEx3 {
    public static void main(String[] args) {
        FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
        FruitBox<Apple> appleBox = new FruitBox<Apple>();

        fruitBox.add(new Apple());
        fruitBox.add(new Grape());


        appleBox.add(new Apple());
        appleBox.add(new Apple());
        // appleBox.add(new Grape()); // error java: incompatible types: ch12.Grape cannot be converted to ch12.Apple

        System.out.println(Juicer.makeJuice(fruitBox));
        System.out.println(Juicer.makeJuice(appleBox));
    }
}

class FruitBox<T extends Fruit> extends Box0423<T> {}

class Box0423<T> {
    ArrayList<T> list = new ArrayList<>();
    void add(T item) {
        list.add(item);
    }
    T get(int index) {
        return list.get(index);
    }
    int size() {
        return list.size();
    }
    ArrayList<T> getList() {
        return list;
    }
    public String toString() {
        return list.toString();
    }
}





