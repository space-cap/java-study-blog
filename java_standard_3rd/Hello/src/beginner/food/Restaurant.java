package beginner.food;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    List<Food> foods = new ArrayList<>();

    void addMenu(Food food) {
        for (var f : foods) {
            if (f.getName().equals(food.getName())) { // ==는 객체의 주소(참조)를 비교
                return;
            }
        }
        foods.add(food);
    }

    void displayMenu() {
        foods.forEach(f -> {
            System.out.println("저희 음식점의 메뉴는 " + f.getName() + " 입니다.");
            System.out.println("가격은 " + f.getPrice() + "원 입니다.");
        });
    }
}
