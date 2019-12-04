package monadstreams;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class School2 {
    public static <E> void forEach(Iterable<E> ls, Consumer<E> op) {
        for (E e : ls) {
            op.accept(e);
        }
    }

    public static <E, F> List<F> map(Iterable<E> ls, Function<E, F> op) {
        List<F> res = new ArrayList<>();
        for (E e : ls) {
            F f = op.apply(e);
            res.add(f);
        }
        return res;
    }

    public static <E> List<E> filter(Iterable<E> ls, Predicate<E> crit) {
        List<E> res = new ArrayList<>();
        for (E s : ls) {
            if (crit.test(s)) {
                res.add(s);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<Student> roster = new ArrayList<>(Arrays.asList(
                new Student("Fred", 3.2, "Math", "Physics"),
                new Student("Jim", 2.2, "Art"),
                new Student("Sheila", 3.8,
                        "Math", "Physics", "Engineering", "Quantum Mechanics")
        ));
        forEach(roster, s -> System.out.println(s));
        forEach(filter(roster, s -> s.getCourses().size() > 3), s -> System.out.println(s));

        forEach(
                map(roster, s -> s.getName() + " has grade " + s.getGpa()),
                s -> System.out.println("> " + s));

        SuperIterable<Student> sis = new SuperIterable<>(roster);

        sis
                .filter(s -> s.getGpa() > 3)
                .map(s -> s.getName() + " has grade " + s.getGpa())
                .forEach(s -> System.out.println("-- " + s));

        sis
//                .filter(s -> s.getGpa() > 3)
                .flatMap(s -> new SuperIterable<>(s.getCourses()).map(c -> s.getName() + " takes " + c))
                .forEach(s -> System.out.println("++ " + s));

        roster.stream()
                .filter(s -> s.getGpa() > 3)
                .map(s -> s.getName() + " has grade " + s.getGpa())
                .forEach(s -> System.out.println("S: " + s));

        roster.stream()
//                .filter(s -> s.getGpa() > 3)
                .flatMap(s -> s.getCourses().stream().map(c -> s.getName() + " takes " + c))
                .forEach(s -> System.out.println("S:++ " + s));

        SuperIterable<Student> sis2 =
                sis.peek(s -> System.out.println("SIS Peek: " + s));
        sis2.forEach(s -> System.out.println("out: " + s));
        sis2.forEach(s -> System.out.println("out: " + s));

        Stream<Student> ss = roster.stream()
                .peek(s -> System.out.println("Peek: " + s));
        ss.forEach(s -> System.out.println("out: " + s));
//        ss.forEach(s -> System.out.println("out: " + s));

        roster.stream()
                .mapToDouble(s -> s.getGpa())
                .mapToObj(d -> "A grade of " + d)
                .forEach(s -> System.out.println(s));

        IntStream.iterate(1, x -> x + 1)
                .peek(x -> System.out.println("peeking " + x))
                .limit(10)
                .forEach(x -> System.out.println(x));

        System.out.println("All greater? " +
                IntStream.iterate(10, x -> x - 1)
                        .peek(x -> System.out.println("processing " + x))
                        .allMatch(x -> x > 5));

        long count = Stream.of("a", "b", "c")
                .peek(x -> System.out.println("observing " + x))
                .count();
        System.out.println("count is " + count);

        try {
            Stream.of("a", "b", "c")
                    .peek(x -> {
                        if (x == "b") throw new RuntimeException();
                    })
                    .forEach(x -> System.out.println(x));
        } catch (Throwable t) {
            System.out.println("Caught " + t);
        }

    }
}
