package ch06;

class Data3 {
    int x;
}

public class Ex6_8 {
    public static void main(String[] args) {
        Data3 d1 = new Data3();
        System.out.println("main() : d = " + Integer.toHexString(System.identityHashCode(d1)));
        d1.x = 10;

        Data3 d2 = copy(d1);
        System.out.println("main() : d = " + Integer.toHexString(System.identityHashCode(d2)));
        System.out.println("d1.x = " + d1.x);
        System.out.println("d2.x = " + d2.x);

    }

    static Data3 copy(Data3 d) {
        System.out.println("copy() : d = " + Integer.toHexString(System.identityHashCode(d)));
        Data3 tmp = new Data3();
        System.out.println("copy() : tmp = " + Integer.toHexString(System.identityHashCode(tmp)));
        tmp.x = d.x ;
        return tmp;
    }
}
