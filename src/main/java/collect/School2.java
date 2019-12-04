package collect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class School2 {
    public static String getLetterGrade(Student s) {
        double gpa = s.getGpa();
        if (gpa > 3.6) return "A";
        if (gpa > 3.1) return "B";
        if (gpa > 2.5) return "C";
        if (gpa > 2.0) return "D";
        return "E";
    }
    public static void main(String[] args) {
        List<Student> roster = new ArrayList<>(Arrays.asList(
                new Student("Fred", 3.2, "Math", "Physics"),
                new Student("Jim", 2.2, "Art"),
                new Student("Jim2", 2.8, "Art"),
                new Student("Jim3", 3.3, "Art"),
                new Student("Jim4", 3.7, "Art"),
                new Student("Sheila", 3.8,
                        "Math", "Physics", "Engineering", "Quantum Mechanics")
        ));
        Map<String, List<Student>> map1 = roster.stream()
                .collect(Collectors.groupingBy(s -> getLetterGrade(s)));
        map1.forEach((k, v) -> System.out.println("Students with grade " + k + ": " + v));

        Map<String, Long> map2 = roster.stream()
                .collect(Collectors.groupingBy(s -> getLetterGrade(s),
                        Collectors.counting()));
        map2.forEach((k, v) -> System.out.println("There are " + v + " students with grade " + k));

        Map<String, String> map3 = roster.stream()
                .collect(Collectors.groupingBy(s -> getLetterGrade(s),
                        Collectors.mapping(t -> t.getName(), Collectors.joining(", "))));
        map3.forEach((k, v) -> System.out.println("Students with grade " + k + " are: " + v));
    }
}
