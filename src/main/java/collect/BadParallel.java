package collect;

import java.util.stream.IntStream;

public class BadParallel {
    public static void main(String[] args) {
        int [] data = { 0 };

        IntStream.generate(() -> 1)
                .parallel()
                .limit(1_000_000)
                .peek(x -> data[0]++)
                .reduce((a, b) -> a + b);
        System.out.println("data[0]=" + data[0]);
    }
}
