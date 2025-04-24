package ch15;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStreamEx1 {
    public static void main(String[] args) {
        try {
            FileOutputStream fos = new FileOutputStream("123.txt", true); // 기본 false 지우고 다시, true 끝에 이어서 쓴다.
            BufferedOutputStream bos = new BufferedOutputStream(fos, 5);

            for (int i = '1'; i <= '9'; i++) {
                bos.write(i);
            }
            //fos.close(); // 12345 까지 만 파일에 쓰여진다.
            bos.close(); // 123456789
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("hello world");

    }

}
