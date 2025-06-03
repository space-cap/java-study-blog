package pattern;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

class BigChar {
    private char charName;
    private String fontData;

    public BigChar(char charName) {
        this.charName = charName;

        try {
            String fileName = "resources/big" + charName + ".txt";
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

class BigCharFactory {
    // 이미 만든 BigChar 인스턴스를 관리
    private Map<String,BigChar> pool = new HashMap<>();
    // Singleton 패턴
    private static BigCharFactory singleton = new BigCharFactory();

    // 생성자
    private BigCharFactory() {
    }

    // 유일한 인스턴스를 얻는다
    public static BigCharFactory getInstance() {
        return singleton;
    }

    // BigChar 인스턴스 생성(공유)
    public synchronized BigChar getBigChar(char charname) {
        BigChar bc = pool.get(String.valueOf(charname));
        if (bc == null) {
            // 여기서 BigChar 인스턴스를 생성
            bc = new BigChar(charname);
            pool.put(String.valueOf(charname), bc);
        }
        return bc;
    }
}


class BigString {
    private BigChar[] bigChars;

    public BigString(String str) {
        BigCharFactory factory = BigCharFactory.getInstance();
        bigChars = new BigChar[str.length()];
        for (int i = 0; i < str.length(); i++) {
            bigChars[i] = factory.getBigChar(str.charAt(i));
        }
    }

    public void print() {
        for (BigChar bc : bigChars) {
            bc.print();
        }
    }
}


public class FlyweightEx1 {
    public static void main(String[] args) {
        System.out.println("Hello, Flyweight!");

//        if(args.length == 0) {
//            System.out.println("Usage: java main digits");
//            System.out.println("example java main 12121212");
//            System.exit(0);
//        }



        BigString bs = new BigString("1");
        bs.print();


    }
}
