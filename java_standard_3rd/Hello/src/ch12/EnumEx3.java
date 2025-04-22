package ch12;

enum Transportation {
    BUS(100) {
        @Override
        int fare(int distance) {
            return BASIC_FARE * distance;
        }
    },
    TRAIN(200) {
        @Override
        int fare(int distance) {
            return BASIC_FARE * distance;
        }
    },
    SHIP(300) {
        @Override
        int fare(int distance) {
            return BASIC_FARE * distance;
        }
    },
    AIRPLANE(400) {
        @Override
        int fare(int distance) {
            return BASIC_FARE * distance;
        }
    };

    protected final int BASIC_FARE;

    Transportation(int basicFare) {
        this.BASIC_FARE = basicFare;
    }

    public int getBasicFare() {
        return BASIC_FARE;
    }

    abstract int fare(int distance);
}


public class EnumEx3 {
    public static void main(String[] args) {
      System.out.println("bus fare: + " + Transportation.BUS.fare(100));
    }
}
