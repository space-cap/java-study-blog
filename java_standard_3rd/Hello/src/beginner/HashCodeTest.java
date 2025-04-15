package beginner;

public class HashCodeTest {
    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();

        System.out.println(obj1.hashCode()); // 예: 12345678
        System.out.println(obj2.hashCode()); // 예: 87654321

        System.out.println(System.identityHashCode(obj1)); // 위와 같은 값

    }
}
