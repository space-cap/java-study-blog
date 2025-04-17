package ch13;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ThreadEx7 {
    public static void main(String[] args) {
        ThreadEx7_1 th1 = new ThreadEx7_1();
        th1.start(); // Start the first thread

        String input = JOptionPane.showInputDialog("아무 값이나 입력하세요");
        System.out.println("입력하신 값은 " + input + "입니다.");

        List<Integer> l1 = new ArrayList<Integer>();
        ArrayList<Integer> l2 = new ArrayList<Integer>();

    }
}

class ThreadEx7_1 extends Thread {
    public void run() {
        for(int i=10; i>0; i--) {
            System.out.println(i);
            try {
                Thread.sleep(1000); // 1초 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


