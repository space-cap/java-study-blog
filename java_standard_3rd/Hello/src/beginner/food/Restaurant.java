package beginner.food;

import java.util.HashMap;
import java.util.Map;

public class Restaurant {
    Map<String, Food> foods = new HashMap<>(); // food name , Food

    void addMenu(Food food) {
        if (!foods.containsKey(food.getName())) {
            foods.put(food.getName(), food);
        }
    }

    void displayMenu() {
        foods.forEach((k, v) -> {
            System.out.println("저희 음식점의 메뉴는 " + v.getName() + " 입니다.");
            System.out.println("가격은 " + v.getPrice() + "원 입니다.");
        });
    }
}
