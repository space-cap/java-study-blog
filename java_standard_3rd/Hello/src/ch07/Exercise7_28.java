package ch07;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Exercise7_28 {
    public static void main(String[] args) {
        /*
        Frame f = new Frame();
        f.addWindowListener(new EventHandler() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        */

    }
}

class EventHandler extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        e.getWindow().setVisible(false);
        e.getWindow().dispose();
        System.exit(0);
    }
}
