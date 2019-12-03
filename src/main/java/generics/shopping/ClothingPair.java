package generics.shopping;

//public class ClothingPair<E extends Sized, F> extends Pair<E> {
//    F s = "Hello";

// if a class constraint is used, it must be only one, and FIRST
public class ClothingPair<E extends /*Object & */Sized & Colored> extends Pair<E> {
    public ClothingPair(E left, E right) {
        super(left, right);
    }

    public boolean matched() {
        return left.getSize() == right.getSize()
                && left.getColor().equals(right.getColor());
    }

    public static <E extends Sized & Colored> boolean match(E left, E right) {
        return left.getSize() == right.getSize()
                && left.getColor().equals(right.getColor());
    }
}
