package ch02;

public class OverflowEx {
    public static void main(String[] args) {
        short sMin = -32768;
        short sMax = 32767;
        char cMin = 0;
        char cMax = 65535;

        System.out.println("sMin = " + sMax);
        System.out.println("sMin-1 = " + (short)(sMax-1));

        System.out.println(((Object) sMin).getClass().getName());

        var f = sMin+1;
        System.out.println(((Object) f).getClass().getName());

        f = sMin-1;
        System.out.println(((Object) f).getClass().getName());
    }
}
