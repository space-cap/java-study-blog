package mini_project.pokergame1;

public enum Suit {
    CLUB(1, "♣"), DIAMOND(2, "♦"), HEART(3, "♥"), SPADE(4, "♠");

    private final int priority;
    private final String symbol;

    Suit(int priority, String symbol) {
        this.priority = priority;
        this.symbol = symbol;
    }

    public int getPriority() {
        return priority;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}

