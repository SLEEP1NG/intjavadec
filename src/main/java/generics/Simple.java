package generics;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Simple {
    public static void breakAList(List l) {
        l.add(2, LocalDate.now());
    }

    public static void main(String[] args) {
        // untyped RHS can cause problems...
//        List<String> names = new ArrayList(Arrays.asList("Alan", "Bill", LocalDate.now()));
        // Explicit typed RHS better, but likely unnecessary (since Java 7)
//        List<String> names = new ArrayList<String>(Arrays.asList("Alan", "Bill", LocalDate.now()));
        List<String> names = new ArrayList<>(Arrays.asList("Alan", "Bill"/*, LocalDate.now()*/));

//        names = Collections.checkedList(names, String.class);

        names.add("Fred");
        names.add("Jim");
        names.add("Sheila");
//        names.add(LocalDate.now());


//        for (Object o : names) {
        for (String o : names) {
            System.out.println("> " + o);
        }

        breakAList(names);
        System.out.println("Name two is " + names.get(2));

        String s = names.get(2);
    }
}
