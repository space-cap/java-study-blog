package pattern;

import java.util.HashMap;
import java.util.Map;

interface Product extends Cloneable {
    public abstract void use(String s);
    public abstract Product createCopy();
}

class Manager {
    private Map<String, Product> products = new HashMap<>();
    public void registerProduct(String name, Product p) {
        products.put(name, p);
    }
    public Product create(String name) {
        Product p = products.get(name);
        return p.createCopy();
    }
}

class MessageBox implements Product {
    private char decochar;
    public MessageBox(char decochar) {
        this.decochar = decochar;
    }

    @Override
    public void use(String s) {
        System.out.println("use message box " + decochar);
    }

    @Override
    public Product createCopy() {
        Product p = null;
        try {
            p = (Product)clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return p;
    }
}

class UnderlinePen implements Product {
    private char ulchar;
    public UnderlinePen(char ch) {
        this.ulchar = ch;
    }

    @Override
    public void use(String s) {
        System.out.println("use underline pen");
    }

    @Override
    public Product createCopy() {
        Product p = null;
        try {
            p = (Product)clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return p;
    }
}

public class PrototypeEx1 {
    public static void main(String[] args) {
        System.out.println("hello PrototypeEx1");

        Manager manager = new Manager();
        UnderlinePen upen = new UnderlinePen('_');
        MessageBox mbox = new MessageBox('+');
        MessageBox mbox2 = new MessageBox('-');

        manager.registerProduct("upen", upen);
        manager.registerProduct("mbox", mbox);
        manager.registerProduct("mbox2", mbox2);

        Product p1 = manager.create("upen");
        p1.use("hello world");

        Product p2 = manager.create("mbox");
        p2.use("hello world");

        Product p3 = manager.create("mbox2");
        p3.use("hello world");
    }
}
