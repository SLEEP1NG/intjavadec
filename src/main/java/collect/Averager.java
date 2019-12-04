package collect;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;

class Average {
    private final double sum;
    private final long count;

    public Average(double sum, long count) {
        this.sum = sum;
        this.count = count;
    }

    public Average include(double d) {
        return new Average(sum + d, count + 1);
    }

    public Average merge(Average other) {
        return new Average(this.sum + other.sum, this.count + other.count);
    }

    public Optional<Double> get() {
        if (count != 0) return Optional.of(sum / count);
        else return Optional.empty();
    }
}
public class Averager {
    public static void main(String[] args) {
        long start = System.nanoTime();
        ThreadLocalRandom.current().doubles(2_000_000_000, -Math.PI, +Math.PI)
                .parallel()
                .boxed()
                .reduce(new Average(0, 0), (a, d) -> a.include(d), (a, a1) -> a.merge(a1))
                .get()
                .ifPresent(v -> System.out.println("Average is " + v));
        long time = System.nanoTime() - start;
        System.out.printf("Time for samples %7.5f\n", (time / 1_000_000_000.0));
    }
}
