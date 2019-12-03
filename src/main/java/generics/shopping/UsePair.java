package generics.shopping;

import java.time.LocalDate;

public class UsePair {
    public static void main(String[] args) {
        Pair<String> p = new Pair<>("Fred", "Jones");

        ClothingPair<Glove> pg = new ClothingPair<>(
                new Glove(9, "Red"),
                new Glove(10, "Red"));
        System.out.println("Gloves are " + pg);
        System.out.println("Gloves match? " + pg.matched());

        ClothingPair<Glove> pg2 = new ClothingPair<>(
                new Glove(10, "Red"),
                new Glove(10, "Green"));
        System.out.println("Gloves are " + pg2);
        System.out.println("Gloves match? " + pg2.matched());

        // here, type value of E for expression ps is *assigned*
        ClothingPair<Sock> ps = new ClothingPair<>(new Sock(10, "Black"),
                new Sock(11, "Blue"));

        // Type value for E in this invocation is *inferred*
        ClothingPair.match(
                new Sock(10, "Black"),
                new Sock(11, "Blue"));

        // Type value for E in this invocation is *assigned*
        ClothingPair.<Sock>match(
                new Sock(10, "Black"),
                new Sock(11, "Blue"));
    }
}
