package beginner.food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Restaurant {
    ///List<Food> foods = new ArrayList<>();

    Map<String, Food> foods = new HashMap<>();

    void addMenu(Food food) {
//        for (var f : foods) {
//            if (f.getName().equals(food.getName())) { // ==는 객체의 주소(참조)를 비교
//                return;
//            }
//        }
//        foods.add(food);

        if(!foods.containsKey(food.getName())) {
            foods.put(food.getName(), food);
        }
    }

    void displayMenu() {
        foods.forEach((k,v) -> {
            System.out.println("저희 음식점의 메뉴는 " + v.getName() + " 입니다.");
            System.out.println("가격은 " + v.getPrice() + "원 입니다.");
        });
    }
}
