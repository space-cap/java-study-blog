package ch07;

class Tv {
    boolean power;
    int channel;

    void power() {
        power = !power;
    }

    void channelUp() {
        channel++;
    }

    void channelDown() {
        channel--;
    }
}


class CaptionTv extends Tv {
    String text;
    void caption() {
        if (power) {
            System.out.println(text);
        }
    }
}


public class ex7_14 {
    public static void main(String[] args) {
        Tv t = new CaptionTv();
        // CaptionTv c = new Tv(); // incompatible types: ch07.Tv cannot be converted to ch07.CaptionTv

        if(t instanceof CaptionTv) {
            CaptionTv c = (CaptionTv) t; // 다운캐스팅
            System.out.println("다운캐스팅 (Downcasting)");
        } else {
            System.out.println("t는 CaptionTv의 인스턴스가 아닙니다.");
        }
    }
}
