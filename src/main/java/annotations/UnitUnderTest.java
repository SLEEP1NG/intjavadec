package annotations;

//@RunMe
public class UnitUnderTest {
//    @SetMe(nameKey = "Second", count = 7)
    @SetMe(value = "Second", count = 4)
    private String name = "unset";

    @RunMe
    public void doThis() {
        System.out.println("doThis running...");
    }

    public void doTheOther() {
        System.out.println("doTheOther running...");
    }

    @RunMe
    private void doThat() {
        System.out.println("doThat (private) running...");
    }

    @Override
    public String toString() {
        return "This is a UnitUnderTest: name is " + name;
    }
}
