package ch07;

class MyTv2 {
    private boolean isPowerOn;
    private int channel;
    private int volume;

    final int MAX_VOLUME = 100;
    final int MIN_VOLUME = 0;
    final int MAX_CHANNEL = 100;
    final int MIN_CHANNEL = 1;

    boolean getIsPowerOn() {
        return isPowerOn;
    }
    void setIsPowerOn(boolean isPowerOn) {
        this.isPowerOn = isPowerOn;
    }

    int getChannel() {
        return channel;
    }
    void setChannel(int channel) {
        prevChannel = getChannel();
        this.channel = channel;
    }
    int getVolume() {
        return volume;
    }
    void setVolume(int volume) {
        this.volume = volume;
    }

    int prevChannel = 0;
    void gotoPrevChannel() {
        // TODO Auto-generated method stub
    }
}

public class Exercise7_11 {
    public static void main(String[] args) {
        MyTv2 t = new MyTv2();

        t.setChannel(10);
        System.out.println("CH:" + t.getChannel());
        t.setVolume(20);
        System.out.println("VOL:" + t.getVolume());
    }
}
