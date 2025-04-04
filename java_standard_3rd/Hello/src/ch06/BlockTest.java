package ch06;

public class BlockTest {
    static {
        System.out.println("static { }");
    }

    {
        System.out.println("{ }");
    }

    BlockTest() {
        System.out.println("생성자 BlockTest()");
    }

    public static void main(String[] args) {
        System.out.println("BlockTest bt = new BlockTest();");
        BlockTest bt = new BlockTest();

        System.out.println("BlockTest bt2 = new BlockTest();");
        BlockTest bt2 = new BlockTest();
    }
}
