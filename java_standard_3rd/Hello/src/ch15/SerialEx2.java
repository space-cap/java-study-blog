package ch15;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class SerialEx2 {
    public static void main(String[] args) {
        try {
            String fileName = "UserInfo.ser";
            FileInputStream fis = new FileInputStream(fileName);
            BufferedInputStream bis = new BufferedInputStream(fis);

            ObjectInputStream in = new ObjectInputStream(bis);
            ArrayList<UserInfo> list = (ArrayList<UserInfo>) in.readObject();
            in.close();

            for (UserInfo u : list) {
                System.out.println(u.name + " " + u.password + " " + u.age);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
