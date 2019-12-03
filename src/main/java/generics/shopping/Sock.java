package generics.shopping;

public class Sock implements Sized, Colored {
    private int size;
    private String color;

    public Sock(int size, String color) {
        this.size = size;
        this.color = color;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Sock{" +
                "size=" + size +
                ", color='" + color + '\'' +
                '}';
    }
}
