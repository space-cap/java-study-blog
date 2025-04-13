package ch09;

public class StringBufferTest {
    public static void main(String[] args) {
        System.out.println("StringBufferTest");

        StringBuffer sb = new StringBuffer();
        System.out.println("초기 capacity: " + sb.capacity());

        int previousCapacity = sb.capacity();
        int i = 0;

        while (sb.length() < 200) {
            sb.append("a");

            if (sb.capacity() != previousCapacity) {
                System.out.printf("length: %3d → capacity 증가! %d → %d%n",
                        sb.length(), previousCapacity, sb.capacity());
                previousCapacity = sb.capacity();
            }

            i++;
        }

    }
}
