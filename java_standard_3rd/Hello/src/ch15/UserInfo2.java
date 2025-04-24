package ch15;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class SuperUserInfo {
    String name;
    String password;
}

public class UserInfo2 extends SuperUserInfo implements Serializable {
    int age;

    public UserInfo2(String name, String password, int age) {
        this.name = name;
        this.password = password;
        this.age = age;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeUTF(name);
        out.writeUTF(password);
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        name = in.readUTF();
        password = in.readUTF();
        in.defaultReadObject();
    }
}

class User {
    String name;
    String password;
}

class AirUser extends User implements Serializable {
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeUTF(name);
        out.writeUTF(password);
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        name = in.readUTF();
        password = in.readUTF();
        in.defaultReadObject();
    }
}