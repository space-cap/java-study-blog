package ch15;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

class UserInfo implements Serializable {
    String name;
    String password;
    int age;

    UserInfo() {
        this("Unknown", "1111", 0);
    }

    UserInfo(String name, String password, int age) {
        this.name = name;
        this.password = password;
        this.age = age;
    }
}


public class SerialEx1 {
    public static void main(String[] args) {
        try {
            String fileName = "UserInfo.ser";
            FileOutputStream fos = new FileOutputStream(fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            ObjectOutputStream out = new ObjectOutputStream(bos);

            UserInfo u1 = new UserInfo("javaman", "1234", 30);
            UserInfo u2 = new UserInfo("javaer", "1234", 20);

            ArrayList<UserInfo> list = new ArrayList<>();
            list.add(u1);
            list.add(u2);

            out.writeObject(list);
            out.close();

            System.out.println("Serialized data is saved in " + fileName);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
