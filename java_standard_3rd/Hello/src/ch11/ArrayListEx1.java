package ch11;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ArrayListEx1 {
    public static void main(String[] args) {
        System.out.println("ArrayListEx1");
        ArrayList list1 = new ArrayList();
        list1.add(new Integer(30));
        list1.add(Integer.valueOf(21));
        list1.add(Integer.valueOf(22));
        list1.add(Integer.valueOf(43));
        list1.add(Integer.valueOf(24));
        list1.add(Integer.valueOf(55));
        list1.add(Integer.valueOf(26));
        /*for(var i : list1) {
            System.out.println(i);
        }*/

        ArrayList list2 = new ArrayList(list1.subList(1,4));
        /*for(var i : list2) {
            System.out.println("list2: " + i);
        }*/

        print(list1, list2);

        Collections.sort(list1);
        Collections.sort(list2);
        print(list1, list2);

        list2.add("B");
        list2.add("C");
        list2.add(3, "A");
        print(list1, list2);

        list2.set(3, "AA");
        print(list1, list2);

    }

    static void print(ArrayList list1, ArrayList list2) {
        System.out.println("list1: " + list1);
        System.out.println("list2: " + list2);
        System.out.println();
    }
}
