package beginner.food;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    List<Food> foods = new ArrayList<>();

    void addMenu(Food food) {
        foods.add(food);
    }

    void displayMenu() {
        System.out.println("저희 음식점의 메뉴는 " + food.getName() + " 입니다.");
        System.out.println("가격은 " + food.getPrice() + "원 입니다.");
    }
}
