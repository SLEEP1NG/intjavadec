package exceptions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class SuperIterable<E> implements Iterable<E> {
    private Iterable<E> self;

    public SuperIterable(Iterable<E> self) {
        this.self = self;
    }

//    public void forEvery(Consumer<E> op) {
//        for (E e : self) {
//            op.accept(e);
//        }
//    }

    // makes SuperIterable "Functor"
    public <F> SuperIterable<F> map(Function<E, F> op) {
        List<F> out = new ArrayList<>();

        for (E e : self) {
            F f = op.apply(e);
            if (f != null) {
                out.add(f);
            }
        }

        return new SuperIterable(out);
    }

    @Override
    public Iterator<E> iterator() {
        return self.iterator();
    }

}
