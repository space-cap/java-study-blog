package ch07;

public class Excrcise7_19 {
    public static void main(String[] args) {
        System.out.println("main hello");

        Buyer719 b = new Buyer719();
        b.buy(new Tv719());
        b.buy(new Computer719());
        b.buy(new Tv719());
        b.buy(new Audio719());
        b.buy(new Computer719());
        b.buy(new Computer719());
        b.buy(new Computer719());

        b.summary();
    }
}


class Buyer719 {
    int money = 1000;
    Product719[] cart = new Product719[3]; // 구입한 제품을 저장하기 위한 배열
    int i = 0; // Product배열 cart에 저장할 index

    void buy(Product719 p) {
        if(p.price > money) {
            System.out.println(p.toString() + "은(는) 살 수 없습니다.");
            return; // 돈이 부족하면 메서드 종료
        }
        money -= p.price; // 제품의 가격을 money에서 차감
        System.out.println(p + "을(를) 구입하셨습니다.");
        add(p); // cart에 제품을 저장
    }

    void add(Product719 p) {
        if(i >= cart.length) {
            Product719 tmp[] = new Product719[cart.length*2];
            System.arraycopy(cart, 0, tmp, 0, cart.length);
            cart = tmp;
        }
        cart[i] = p; // cart에 제품을 저장
        i++; // index 증가
    }

    void summary() {
        int sum = 0; // 구입한 제품의 가격 합계
        for(int i=0; i<cart.length; i++) {
            if(cart[i] == null) break; // cart에 저장된 제품이 없으면 종료
            System.out.print(cart[i].toString() + ", ");
            sum += cart[i].price; // 제품의 가격을 sum에 더함
        }

        System.out.println();
        System.out.println("구입한 제품의 총 가격은 " + sum + "만원입니다.");
        System.out.println("남은 돈은 " + money + "만원입니다.");
    }
}

class Product719 {
    int price; // 제품의 가격

    Product719(int price) {
        this.price = price;
    }
}

class Tv719 extends Product719 {
    Tv719() {
        super(100);
    }

    public String toString() {
        return "TV";
    }
}

class Computer719 extends Product719 {
    Computer719() {
        super(200);
    }

    public String toString() {
        return "Computer";
    }
}

class Audio719 extends Product719 {
    Audio719() {
        super(50);
    }

    public String toString() {
        return "Audio";
    }
}



