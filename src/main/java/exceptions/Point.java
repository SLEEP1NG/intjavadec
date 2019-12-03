package exceptions;

public class Point {
//    private int x, y;
    private int radius, angle; // ENCAPSULATION!! :)

//    public int getX() { return x; }
    public int getX() { return (int)(radius * Math.tan(angle)); }

}
