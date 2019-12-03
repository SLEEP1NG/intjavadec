package exceptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

@FunctionalInterface
interface ExFunction<A, B> {
    B apply(A a) throws Throwable;
}

class Either<L, R> {
    private L left;
    private R right;

    private Either() {}

    public static <L, R> Either<L, R> success(R right) {
        Either<L, R> self = new Either<>();
        self.right = right;
        return self;
    }

    public static <L, R> Either<L, R> failure(L left) {
        Either<L, R> self = new Either<>();
        self.left = left;
        return self;
    }

    public boolean isSuccess() {
        return left == null;
    }

    public boolean isFailure() {
        return left != null;
    }

    public Either<L, R> recover(Function<L, Either<L, R>> op) {
        if (left != null) {
            return op.apply(left);
        } else return this;
    }

    public void ifFailure(Consumer<L> op) {
        if (left != null) op.accept(left);
    }

    public void ifSuccess(Consumer<R> op) {
        if (left == null) op.accept(right);
    }

    public R get() {
        return right;
    }

    public static <A, B> Function<A, Either<Throwable, B>> wrap(ExFunction<A, B> op) {
        return a -> {
            try {
                return Either.success(op.apply(a));
            } catch (Throwable t) {
                return Either.failure(t);
            }
        };
    }
}

public class EitherReadFiles {


//    public static Either<Throwable, Stream<String>> getLines(String fn) {
//        try {
//            return Either.success(Files.lines(Paths.get(fn)));
//        } catch (IOException e) {
//            return Either.failure(e);
//        }
//    }

    public static void main(String[] args) {
        Stream.of("a.txt", "b.txt", "c.txt")
//                .map(fn -> getLines(fn))
                .map(Either.wrap(fn -> Files.lines(Paths.get(fn))))
                .peek(e -> e.ifFailure(
                        t -> System.out.println(t.getClass().getName() + ": " + t.getMessage())))
                .map(e -> e.recover(Either.wrap(fn -> Files.lines(Paths.get("backup.txt")))))
                .peek(e -> e.ifFailure(
                        t -> System.out.println(t.getClass().getName() + ": " + t.getMessage())))
                .filter(e -> e.isSuccess())
                .flatMap(opt -> opt.get())
                .forEach(s -> System.out.println(s));
    }
}
