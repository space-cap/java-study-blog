package beginner;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Room<TT> {
    TT t;

    void set(TT t) {
        this.t = t;
    }

    TT get() {
        return t;
    }
}

class Man {
    int age;
}

class Woman {
    int age;
}

class Home {
    Room<Man> roomMan;

    Home() {
        roomMan = new Room<>();
        Man man = new Man();
        roomMan.set(man);
    }
}

public class TTTestEX1 {
    public static void main(String[] args) {
        System.out.println("TTTestEX1");

        Home home = new Home();


        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new LinkedList<>();
    }
}
