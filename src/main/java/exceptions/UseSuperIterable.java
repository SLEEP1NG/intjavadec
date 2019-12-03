package exceptions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UseSuperIterable {
    public static void main(String[] args) {
        SuperIterable<String> sis = new SuperIterable<>(
                Arrays.asList("Fred", "Jim", "Sheila"));

//        for (String s : sis) {
//            System.out.println("> " + s);
//        }
        sis.forEach(s -> System.out.println("> " + s));

        sis
                .map(s -> s.toUpperCase())
                .forEach(s -> System.out.println(">> " + s));


        Map<String, String> names = new HashMap<>();
        names.put("Fred", "Jones");

        String lookup = "Freddy";

        String last = names.get(lookup);
        if (last != null) {
            String message = last.toUpperCase();
            message = "Dear " + message;
            System.out.println(message);
        }

        new SuperIterable<>(Arrays.asList(names))
                .map(m -> m.get(lookup))
                .map(m -> m.toUpperCase())
                .map(m -> "Dear " + m)
                .forEach(m -> System.out.println(m));

        Optional.of(names)
                .map(m -> m.get(lookup))
                .map(m -> m.toUpperCase())
                .map(m -> "Dear " + m)
                .ifPresent(m -> System.out.println(m));
    }
}
