package functional.students;

import java.util.Arrays;
import java.util.List;

public class School {
    public static void show(List<Student> ls) {
        for (Student s : ls) {
            System.out.println("> " + s);
        }
        System.out.println("---------------------------");
    }
    public static void main(String[] args) {
        List<Student> roster = Arrays.asList(
                new Student("Fred", 3.2, "Math", "Physics"),
                new Student("Jim", 2.2, "Art"),
                new Student("Sheila", 3.8,
                        "Math", "Physics", "Engineering", "Quantum Mechanics")
        );
        show(roster);
    }
}
