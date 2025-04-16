package mentor;

import java.util.ArrayList;

class MyArrayList {
    private Object[] arr;
    private int size;

    public MyArrayList() {
        arr = new Object[10];
        size = 0;
    }

    public void add(Object obj) {
        if (size == arr.length) {
            resize();
        }
        arr[size++] = obj;
    }

    private void resize() {
        Object[] newArr = new Object[arr.length * 2];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        arr = newArr;
    }

    public Object get(int index) {
        return arr[index];
    }

    public int size() {
        return size;
    }
}



public class ArrayListTest {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        MyArrayList list = new MyArrayList();

        list.add("lee");
        list.add("young");
        list.add("hyun");

        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        ArrayList list2 = new ArrayList();


    }
}
