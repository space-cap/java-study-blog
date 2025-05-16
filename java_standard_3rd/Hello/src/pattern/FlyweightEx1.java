package pattern;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class BigChar {
    private char charName;
    private String fontData;

    public BigChar(char charName) {
        this.charName = charName;

        try {
            String fileName = "big" + charName + ".txt";
            StringBuilder sb = new StringBuilder();
            for(String line : Files.readAllLines(Paths.get(fileName))) {
                sb.append(line);
                sb.append("\n");
            }
            this.fontData = sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void print() {
        System.out.println(fontData);
    }
}





public class FlyweightEx1 {
    public static void main(String[] args) {
        System.out.println("Hello, Flyweight!");



    }
}
