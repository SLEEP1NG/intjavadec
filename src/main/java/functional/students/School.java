package functional.students;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@FunctionalInterface
interface StudentCriterion {
    boolean test(Student s);
//    boolean breakIt();
    default StudentCriterion negate(/*StudentCriterion this*/) {
        return s -> !this.test(s);
    }
}

class SmartStudentCriterion implements StudentCriterion {
    @Override
    public boolean test(Student s) {
        return s.getGpa() > 3.0;
    }
}

class EnthusiasticStudentCriterion implements StudentCriterion {
    @Override
    public boolean test(Student s) {
        return s.getCourses().size() > 3;
    }
}

public class School {
    public static void show(List<Student> ls) {
        for (Student s : ls) {
            System.out.println("> " + s);
        }
        System.out.println("---------------------------");
    }

    public static List<Student> getByCriterion(List<Student> ls, StudentCriterion crit) {
        List<Student> res = new ArrayList<>();
        for (Student s : ls) {
            if (crit.test(s)) {
                res.add(s);
            }
        }
        return res;
    }

    //    public static List<Student> getSmart(List<Student> ls, double threshold) {
//        List<Student> res = new ArrayList<>();
//        for (Student s : ls) {
//            if (s.getGpa() > threshold) {
//                res.add(s);
//            }
//        }
//        return res;
//    }
//
//    public static List<Student> getEnthuiastic(List<Student> ls, int threshold) {
//        List<Student> res = new ArrayList<>();
//        for (Student s : ls) {
//            if (s.getCourses().size() > threshold) {
//                res.add(s);
//            }
//        }
//        return res;
//    }
//
    public static void main(String[] args) {
        List<Student> roster = Arrays.asList(
                new Student("Fred", 3.2, "Math", "Physics"),
                new Student("Jim", 2.2, "Art"),
                new Student("Sheila", 3.8,
                        "Math", "Physics", "Engineering", "Quantum Mechanics")
        );
        show(roster);
//        show(getSmart(roster, 3.0));
//        show(getEnthuiastic(roster, 3));

        show(getByCriterion(roster, new SmartStudentCriterion()));
        show(getByCriterion(roster, new EnthusiasticStudentCriterion()));
        // Java 1.1 added "anonymous inner classes"
        show(getByCriterion(roster, new
                /*class EnthusiasticStudentCriterion implements*/
                StudentCriterion() {
                    @Override
                    public boolean test(Student s) {
                        return s.getCourses().size() > 3;
                    }
                }
        ));

        show(getByCriterion(roster, /*new
                StudentCriterion() {*/
//                    @Override
                    /*public boolean test*/(Student s) -> {
                        return s.getCourses().size() > 3;
                    }
                /*}*/
        ));

        // Lambda version 1 :)
        show(getByCriterion(roster, (Student s) -> { return s.getCourses().size() > 3; } ));
        // Argument type, if unambiguous, may be omitted (all types, or no types)
        show(getByCriterion(roster, (s) -> { return s.getCourses().size() > 3; } ));
        // Exactly one, untyped, argument? Can omit parentheses...   [zero args? () -> xxx ]
        show(getByCriterion(roster, s -> { return s.getCourses().size() > 3; } ));
        // If method body consists of only a single return statement:
        // omit return, and its semicolon
        // omit curly braces / block
        // known as "Expression lambda" (previously a block lambda-- ENTIRE method body)
        show(getByCriterion(roster, s -> s.getCourses().size() > 3 ));

        StudentCriterion test;
        test = s -> s.getCourses().size() > 3;

        System.out.println("Not very smart");
        show(getByCriterion(roster, Student.getSmartCriterion(2)));
        System.out.println("Somewhat smart");
        show(getByCriterion(roster, Student.getSmartCriterion(3)));
        System.out.println("Very smart");
        show(getByCriterion(roster, Student.getSmartCriterion(3.5)));

        System.out.println("Not Somewhat smart");
        show(getByCriterion(roster, Student.getSmartCriterion(3).negate()));

        CharSequence cs = new String("Hello");
        String str = (String)cs; // normal cast "reveals secrets" but does not change type

        // this cast CHANGES the type of the object created
        Object tryThis = (StudentCriterion)(s -> s.getGpa() > 3);
        System.out.println("Type of tryThis " + tryThis.getClass().getName());
        Method [] methods = tryThis.getClass().getMethods();
        for (Method m : methods) {
            System.out.println(m);
        }
    }
}
