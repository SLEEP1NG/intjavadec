package generics.noreify;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex1 {
    public static <F, E extends F> F[] getArray(List<E> l, Class<F> clazz) {
//        E[] rv = (E[])new Object[l.size()];
        F[] rv = (F[]) Array.newInstance(clazz, l.size());

        for (int i = 0; i < l.size(); i++) {
            rv[i] = l.get(i);
        }
        return rv;
    }

    public static void breakList(List l) {
        l.add(new Object());
    }
//    public static void breakList(List<LocalDate> l) {
//    }
//
//    public static void breakList(List<String> l) {
//    }

    public static void breakArray(Object [] oa) {
        oa[0] = new Object();
    }

    public static void main(String[] args) {
        List<String> ls = new ArrayList<>(Arrays.asList("Fred", "Jim", "Sheila"));
        List<Object> lo = new ArrayList<>(Arrays.asList(new Object()));

//        breakList(ls);
        System.out.println("list now corrupt");

        Object [] oa = {new Object()};
        String [] sa = {"Hello"};

//        breakArray(sa); // Compiler knows that the array can protect itself...

        System.out.println("Type of ls is " + ls.getClass().getName());
        System.out.println("Type of lo is " + lo.getClass().getName());
        System.out.println("Type of oa is " + oa.getClass().getName());
        System.out.println("Type of sa is " + sa.getClass().getName());
        CharSequence [] names = getArray(ls, CharSequence.class);
        System.out.println("type of names is " + names.getClass().getName());
    }
}
