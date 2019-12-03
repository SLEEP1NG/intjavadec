package generics.variance;

import java.util.Arrays;
import java.util.List;

class Taxable {}
class Individual extends Taxable {}
class Retiree extends Individual {}
class Corporation extends Taxable {}

public class WithList {

    public static void doTaxes(Taxable t) {}

    public static void doBulkTaxes(List<? extends Taxable> lt) {
        for (Taxable t : lt) {
            doTaxes(t);
//            if (t instanceof Corporation){}  // NO NO NO!
//            t.doStuff(); // each subclass of taxable implements doStuff in the "right way for that type"
        }
//        lt.add(new Corporation());
    }

    // super means "is assignable FROM ..."
    public static void addClients(List<? super Individual> li) {
        li.add(new Individual());
//        Individual i = li.get(0);
    }

    public static void main(String[] args) {
        List<Taxable> lt = Arrays.asList(
                new Individual(), new Retiree(), new Corporation()
        );
        doBulkTaxes(lt);

        List<Individual> li = Arrays.asList(new Individual(), new Retiree());
        addClients(li);
        doBulkTaxes(li); // () Breaks Liskov substitution principle

        addClients(lt);
    }
}
