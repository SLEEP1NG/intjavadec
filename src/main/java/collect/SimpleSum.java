package collect;

import java.util.stream.IntStream;

public class SimpleSum {
    public static void main(String[] args) {
        IntStream.iterate(1, x -> x + 1)
                .limit(10)
                .reduce((a, b) -> a + b)
                .ifPresent(x -> System.out.println("Sum is " + x));
    }
}
