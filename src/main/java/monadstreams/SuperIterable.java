package monadstreams;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {
    private Iterable<E> self;

    public SuperIterable(Iterable<E> self) {
        this.self = self;
    }

//    public SuperIterable<E> flatten() {
//        List<E> res = new ArrayList<>();
//        for (E e : self) {
//            if (e instanceof List) {
//                ((List<E>) e).forEach(i -> res.add(i));
//            } else res.add(e);
//        }
//        return new SuperIterable<>(res);
//    }

    // map makes our "bucket o'data" into a "Functor"
    public <F> SuperIterable<F> map(Function<E, F> op) {
        List<F> res = new ArrayList<>();
        for (E e : self) {
            F f = op.apply(e);
            res.add(f);
        }
        return new SuperIterable<>(res);
    }

    public SuperIterable<E> peek(Consumer<E> op) {
        self.forEach(e -> op.accept(e));
        return this;
    }

    // flatMap ... makes our bucket into a "Monad"
    public <F> SuperIterable<F> flatMap(Function<E, SuperIterable<F>> op) {
        List<F> res = new ArrayList<>();
        for (E e : self) {
            SuperIterable<F> f = op.apply(e);
            f.forEach(f1 -> res.add(f1));
        }
        return new SuperIterable<>(res);
    }

    public SuperIterable<E> filter(Predicate<E> crit) {
        List<E> res = new ArrayList<>();
        for (E s : self) {
            if (crit.test(s)) {
                res.add(s);
            }
        }
        return new SuperIterable<>(res);
    }

    @Override
    public Iterator<E> iterator() {
        return self.iterator();
    }
}
