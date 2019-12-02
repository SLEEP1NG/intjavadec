package functional.students;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class School2 {
    public static void show(List<?> ls) {
        for (Object s : ls) {
            System.out.println("> " + s);
        }
        System.out.println("---------------------------");
    }

    public static <E> List<E> filter(List<E> ls, Predicate<E> crit) {
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

        /*StudentCriterion*/
        Predicate<Student> sc = s -> s.getGpa() > 3.0;
        System.out.println("sc instanceof StudentCriterion " + (sc instanceof StudentCriterion));
        System.out.println("sc instanceof Predicate " + (sc instanceof Predicate));

        show(filter(Arrays.asList("Fred", "Jim", "Sheila"), s -> s.length() < 6));

        roster.sort((s1, s2) -> s2.getCourses().size() - s1.getCourses().size());
        show(roster);

    }
}
