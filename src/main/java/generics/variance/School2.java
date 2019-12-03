package generics.variance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class School2 {
//    public static void show(List<? extends Object> ls) {
    public static void show(List<?> ls) {
        for (Object s : ls) {
            System.out.println("> " + s);
        }
        System.out.println("---------------------------");
    }

    public static <E> List<E> filter(List<E> ls, Predicate<? super E> crit) {
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
        show(roster);
        show(filter(roster, s -> s.getCourses().size() > 3 ));

        List<String> ls = Arrays.asList("Fred", "Jim", "Sheila");
        Predicate<CharSequence> longCharSequence = s -> s.length() > 3;
        show(filter(ls, longCharSequence));

        List<StringBuilder> lsb = Arrays.asList(
                new StringBuilder("Fred"),
                new StringBuilder("Jim"),
                new StringBuilder("Sheila"));
        show(filter(lsb, longCharSequence));

        String fred = "Fred";
        Predicate<Object> isFred = o -> o == fred;
        show(filter(ls, isFred));


    }
}
