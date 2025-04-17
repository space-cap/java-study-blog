package ch12;

class Box<T> {
    private T item;

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }
}


public class BoxTest {
    public static void main(String[] args) {
        Box<String> b = new Box<>();
        // b.setItem(123); //java: incompatible types: int cannot be converted to java.lang.String
        b.setItem("Hello");
        String s = b.getItem();
        System.out.println(s);

    }
}
