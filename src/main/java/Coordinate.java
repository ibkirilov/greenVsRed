import java.util.Objects;

/**
 *
 * This class represents the coordinate of the cell. Every Object contains x and y.
 * They can be compared, since the equals and hashcode methods are overridden.
 *
 */

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * this method is used to compare the coordinate objects by x and y
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x &&
                y == that.y;
    }

    /**
     * this method is overridden to be able to compare properly the objects.
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
