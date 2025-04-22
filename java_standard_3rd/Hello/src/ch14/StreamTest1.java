package ch14;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class StreamTest1 {
    static List<Integer> createNumbers() {
        return new Random().ints(1, 46)
                .distinct()
                .limit(6)
                .boxed()
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Integer> numbers = createNumbers();
        System.out.println("Generated numbers: " + numbers);
    }

}
