package beginner.food;

public class RestaurantDemo {
    public static void main(String[] args) {
        Restaurant songaheaven = new Restaurant();
        Food menu = new Food("돈까스", 12000);

        songaheaven.addMenu(menu);
        songaheaven.displayMenu();




    }
}
