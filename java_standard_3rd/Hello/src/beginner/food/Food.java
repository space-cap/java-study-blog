package beginner.food;

public class Food {
    private String name;
    private int price;
    private String description; // 설명

    public Food(String name, int price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    /**
     * Price를 셋팅해주는 메소드를 만들어주세요.
     * 금액은 1000원 이상 20000원 이하 여야 합니다.
     * @param price
     */
    public void setPrice(int price) {
        if(price >= 1000 && price <= 20000) {
            this.price = price;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
