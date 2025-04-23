package beginner.food;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    List<Food> foods = new ArrayList<>();

    void addMenu(Food food) {
        if (foods.stream().noneMatch(f -> f.getName().equals(food.getName()))) {
            foods.add(food);
        }

        for(var f : foods) {
            System.out.println(food.getName());
        }
    }

    void displayMenu() {
        //System.out.println("저희 음식점의 메뉴는 " + food.getName() + " 입니다.");
        //System.out.println("가격은 " + food.getPrice() + "원 입니다.");
    }
}
