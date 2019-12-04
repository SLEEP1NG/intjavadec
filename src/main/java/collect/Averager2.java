package collect;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

class Average2 {
    private double sum = 0;
    private long count = 0;

    public Average2() {
    }

    public void include(double d) {
        sum += d;
        count += 1;
    }

    public void merge(Average2 other) {

        sum += other.sum;
        count += other.count;
    }

    public Optional<Double> get() {
        if (count != 0) return Optional.of(sum / count);
        else return Optional.empty();
    }
}

public class Averager2 {
    public static void main(String[] args) {
        long start = System.nanoTime();
//        ThreadLocalRandom.current().doubles(6_000_000_000L, -Math.PI, +Math.PI)
        Stream.iterate(1.0D, x -> ThreadLocalRandom.current().nextDouble(-Math.PI, +Math.PI))
                .limit(2_000_000_000L)
                .parallel()
//                .boxed()
                .collect(() -> new Average2(),
                        (a, d) -> a.include(d),
                        (a, a1) -> a.merge(a1))
                .get()
                .ifPresent(v -> System.out.println("Average is " + v));
        long time = System.nanoTime() - start;
        System.out.printf("Time for samples %7.5f\n", (time / 1_000_000_000.0));
    }
}
