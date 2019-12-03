package generics.shopping;

public class Glove implements Sized, Colored {
    private int size;
    private String color;

    public Glove(int size, String color) {
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
        return "Glove{" +
                "size=" + size +
                ", color='" + color + '\'' +
                '}';
    }
}
