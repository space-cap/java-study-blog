package ch12;

import java.util.ArrayList;
import java.util.Objects;

class Fruit0424 {
}

class Apple0424 extends Fruit0424 {
}

class Grape0424 extends Fruit0424 {
}

class Box0424<T extends Fruit0424> { // 지네릭 타입 T를 선언
    T item;

    void setItem(T item) {
        this.item = item;
    }

    T getItem() {
        return item;
    }
}


public class ExTest1 {
    public static void main(String[] args) {
        System.out.println("Hello World");

        Box0424<Fruit0424> box = new Box0424<>();


        Box0424<?> b1 = new Box0424();
        Box0424<?> b2 = new Box0424<>();
        //Box0424<?> b3 = new Box0424<Object>();
        //Box<Object> b4 = new Box<Fruit>();
        Box0424 b5 = new Box0424<Fruit0424>();
        b5.setItem(new Apple0424());

        Box0424<? extends Fruit0424> b6 = new Box0424<Apple0424>();
        //Box0424<? extends Object> b7 = new Box0424<? extends Fruit0424>(); // 와일드카드로 객체를 생성할 수 없습니다.

    }

    public static ArrayList<? extends Product> merge(
            ArrayList<? extends Product> list, ArrayList<? extends Product> list2) {
        //ArrayList<? extends Product> newList = new ArrayList<>(list);
        ArrayList<Product> newList = new ArrayList<>(list);
        newList.addAll(list2);
        return newList;
    }

    public static <T extends Product> ArrayList<T> merge2(
            ArrayList<T> list, ArrayList<T> list2) {
        ArrayList<T> newList = new ArrayList<>(list);
        newList.addAll(list2);
        return newList;
    }

    public void test3() {
        Box0424 box = null;
        //Box0424<Object> objectBox0424 = null;
    }
}


class Product {
}




