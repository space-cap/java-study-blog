package ch14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.stream.Stream;

public class LambdaTest1 {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        // Using a lambda expression to create a Runnable
        Runnable runnable = () -> System.out.println("Running in a thread");

        // Starting a new thread with the lambda expression
        Thread thread = new Thread(runnable);
        thread.start();

        // Main thread continues
        System.out.println("Main thread continues");

        IntUnaryOperator f = i -> i;
        Function<Integer, Integer> f2 = i -> i;
        IntFunction<Integer> f3 = i -> i;

        int[] arr = new int[5];
        //Arrays.setAll(arr, f);
        //Arrays.setAll(arr, f2::apply);
        //Arrays.setAll(arr, f3::apply);
        Arrays.setAll(arr, i->i);
        System.out.println("Array after setAll: " + Arrays.toString(arr));

        System.out.println("==========================");
        IntFunction<Integer> f4 = i -> i;
        Stream.of(1, 2, 3).map(f4::apply).forEach(System.out::println);

        System.out.println("==========================");
        int[] arr2 = new int[5];
        Arrays.setAll(arr2, (i) -> (int) (Math.random() * 5) + 1);
        System.out.println("random: " + Arrays.toString(arr2));

        System.out.println("==========================");
        //List<Integer> numbers = new ArrayList<>();
        //Arrays.setAll(numbers, (i) -> (int) (Math.random() * 46) + 1);
        Integer[] numbers = new Integer[6];
        Arrays.setAll(numbers, i -> (int)(Math.random() * 45) + 1);
        List<Integer> numberList = Arrays.asList(numbers);
        System.out.println("Generated numbers: " + numberList);

        Object o = new Object() {
            int max(int a, int b) {
                return a > b ? a : b;
            }
        };

        System.out.println("==========================");
        MyFunction myf = new MyFunction() {
            @Override
            public int max(int a, int b) {
                return a > b ? a : b;
            }

            @Override
            public int min(int a, int b) {
                return 0;
            }
        };
        int big = myf.max(10, 20);
        System.out.println("Max value: " + big);

        System.out.println("==========================");

        MyFunction myf2 = (a, b) -> a > b ? a : b;
        int big2 = myf2.max(10, 20);
        int big3 = myf2.min(10, 20);
        System.out.println("Max value: " + big2);
        System.out.println("min value: " + big3);


    }
}

interface MyFunction {
    public abstract int max(int a, int b);
    default int min(int a, int b) {
        return Math.min(a,b);
    }
}
