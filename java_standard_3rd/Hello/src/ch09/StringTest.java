package ch09;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class StringTest {
    public static void main(String[] args) {
        String s = new String("Hello");
        System.out.println("s = " + s);

        char[] c = {'H', 'e', 'l', 'l', 'o'};
        String s2 = new String(c);
        System.out.println("s2 = " + s2);

        StringBuffer sb = new StringBuffer("Hello");
        String s3 = new String(sb);
        System.out.println("s3 = " + s3);

        String s4 = "Hello";
        String s5 = "0123456";
        char c1 = s4.charAt(0);
        char c2 = s4.charAt(1);
        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);

        int i = "aaa".compareTo("aaa");
        int i2 = "aaa".compareTo("bbb");
        int i3 = "bbb".compareTo("aaa");
        System.out.println("i = " + i);
        System.out.println("i2 = " + i2);
        System.out.println("i3 = " + i3);

        String s6 = "Hello";
        String s7 = s6.concat(" World");
        System.out.println("s6 = " + s7);

        String s8 = "abcdefg";
        boolean b1 = s8.contains("bc");
        System.out.println("b1 = " + b1);

        String s9 = "Hello";
        boolean b2 = s9.equalsIgnoreCase("HELLO");
        boolean b3 = s9.equalsIgnoreCase("heLLO");
        System.out.println("b2 = " + b2);
        System.out.println("b3 = " + b3);

        String s10 = "Hello";
        int idx1 = s10.indexOf('o');
        int idx2 = s10.indexOf('k');
        System.out.println("idx1 = " + idx1);
        System.out.println("idx2 = " + idx2);

        String s11 = "Hello";
        int idx3 = s11.indexOf('e', 0);
        int idx4 = s11.indexOf('e', 2);
        System.out.println("idx3 = " + idx3);
        System.out.println("idx4 = " + idx4);

        String s12 = new String("abc");
        String s13 = new String("abc");
        boolean b4 = (s12 == s13);
        boolean b5 = s12.equals(s13);
        boolean b6 = (s12.intern() == s13.intern());
        System.out.println("b4 = " + b4);
        System.out.println("b5 = " + b5);
        System.out.println("b6 = " + b6);

        String s14 = "Hello";
        String s15 = new String("Hello");
        boolean b7 = s14.equals(s15);
        System.out.println("b7 = " + b7);

        System.out.println("Default Charset: " + Charset.defaultCharset());
        System.out.println("file.encoding: " + System.getProperty("file.encoding"));

        String s16 = "가";
        // byte[] bArr = s16.getBytes("UTF-8"); // 예외 처리를 해야 함.
        //try {
        //    byte[] bytes = str.getBytes("UTF-8");
        //} catch (UnsupportedEncodingException e) {
        //    e.printStackTrace();
        //}
        byte[] bArr = s16.getBytes(StandardCharsets.UTF_8);
        System.out.println("UTF-8 bytes: " + joinByteArr(bArr));




    }

    static String joinByteArr(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString();
    }

}
