package ch07;

class Product {
    int price;
    int bonusPoint;

    Product(int price) {
        this.price = price;
        bonusPoint = (int)(price / 10.0);
    }
}

class Tv75 extends Product {
    Tv75() {
        super(100); // Tv의 가격을 100으로 설정
    }

    public String toString() {
        return "Tv";
    }
}

public class Exercise7_5 {
    public static void main(String[] args) {
        Tv75 t = new Tv75();
    }
}
