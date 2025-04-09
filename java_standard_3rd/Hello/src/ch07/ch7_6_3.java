package ch07;

abstract class Player {
    boolean pause;
    int currentPos;

    Player() {
        this.pause = false;
        this.currentPos = 0;
    }

    abstract void play(int pos);
    abstract void stop();

    void play() {
        play(currentPos);
    }

    void pause() {
        if(pause) {
            pause = false;
            play(currentPos);
        }
        else {
            pause = true;
            stop();
        }
    }
}


class AudioPlayer extends Player {
    void play(int pos) {
        System.out.println("AudioPlayer is playing at position: " + pos);
    }

    void stop() {
        System.out.println("AudioPlayer stopped.");
    }
}


class CDPlayer extends Player {
    void play(int pos) {
        System.out.println("CDPlayer is playing at position: " + pos);
    }

    int currentTrack = 0;

    void stop() {
        System.out.println("CDPlayer stopped.");
    }

    void nextTrack() {
        currentTrack++;
        System.out.println("Playing next track.");
    }

    void previousTrack() {
        currentTrack--;
        System.out.println("Playing previous track.");
    }
}

public class ch7_6_3 {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        Player player = new AudioPlayer();
        Player cdPlayer = new CDPlayer();
        cdPlayer.play();


    }
}
