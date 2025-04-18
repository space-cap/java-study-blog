package ch11;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackQueueEx {
    public static void main(String[] args) {
        Stack st = new Stack();
        Queue q = new LinkedList();

        st.push("0");
        st.push("1");
        st.push("2");

        q.offer("0");
        q.offer("1");
        q.offer("2");

        System.out.println("Stack: " + st);
        while(!st.empty()) {
            System.out.println("Stack pop: " + st.pop());
        }

        System.out.println("Queue: " + q);
        while(!q.isEmpty()) {
            System.out.println("Queue poll: " + q.poll());
        }

    }
}
