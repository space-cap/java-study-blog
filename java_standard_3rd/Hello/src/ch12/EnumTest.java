package ch12;


class Card {
    enum Kind { CLOVER, HEART, DIAMOND, SPADE }
    enum Value { TWO, THREE, FOUR}

    final Kind kind;
    final Value value;

    Card(Kind kind, Value value) {
        this.kind = kind;
        this.value = value;
    }
}

public class EnumTest {
    public static void main(String[] args) {
        //if(Card.CLOVER == Card.TWO) {}
        /*if(Card.Kind.CLOVER == Card.Value.TWO) { //java: incomparable types: ch12.Card.Kind and ch12.Card.Value
            System.out.println("same");
        } else {
            System.out.println("not same");
        }*/

        Card c1 = new Card(Card.Kind.CLOVER, Card.Value.TWO);
        if(c1.kind == Card.Kind.CLOVER) {
            System.out.println("same");
        } else {
            System.out.println("not same");
        }

        if(c1.kind.compareTo(Card.Kind.CLOVER) == 0) {
            System.out.println("same");
        } else {
            System.out.println("not same");
        }

        switch (c1.kind) {
            case CLOVER:
                System.out.println("CLOVER");
                break;
            case HEART:
                System.out.println("HEART");
                break;
            case DIAMOND:
                System.out.println("DIAMOND");
                break;
            case SPADE:
                System.out.println("SPADE");
                break;
        }

        enum Direction { EAST, WEST, SOUTH, NORTH }
        Direction[] dArr = Direction.values();
        for(Direction d : dArr) {
            System.out.println(d);
        }

        System.out.println("EAST ordinal: " + Direction.EAST.ordinal());

        Direction d = Direction.valueOf("EAST");
        System.out.println("d valueOf: " + d);
        System.out.println("d valueOf: " + Direction.WEST);

        enum Direction2 {
            NORTH(1), SOUTH(2), EAST(3), WEST(4);

            private final int value;

            // 생성자 추가
            Direction2(int value) {
                this.value = value;
            }

            public int getValue() {
                return value;
            }
        }
        System.out.println("Direction2 EAST ordinal: " + Direction2.EAST.ordinal());
        System.out.println("Direction2 EAST getValue(): " + Direction2.EAST.getValue());


    }
}
