import java.util.HashMap;
import java.util.Objects;


/**
 *
 * This class is used to represent the grid of every generation. Every object has
 * HashMap with Coordinate as key and Color as value. Also there are the dimensions of
 * every grid. This is used for easy getting the sizes of the grids.
 *
 */
public class Grid {
    private HashMap<Coordinate, Color> cells;
    private int width;
    private int height;

    public Grid(HashMap<Coordinate, Color> cells, int width, int height) {
        this.cells = cells;
        this.width = width;
        this.height = height;
    }

    public HashMap<Coordinate, Color> getCells() {
        return cells;
    }

    public void setCells(HashMap<Coordinate, Color> cells) {
        this.cells = cells;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /**
     *
     * This method is used to return the max value of little grids, which we create
     * to examine every cell from the bif grid. I am using the Math class with its
     * min method, because i need the smaller number. Arguments are the coordinate,
     * which is added by one and size of the grid minus one, because the grid
     * coordinates starts from 0.
     *
     * @param coordinate
     * @param maxValue
     * @return
     */
    public int getMaxValue(int coordinate, int maxValue) {
        return Math.min(coordinate + 1, maxValue - 1);
    }

    /**
     *
     * same logic here but for the opposite case. Since the 0 is the smallest possible
     * we need the bigger from 0 and the given one as argument.
     *
     * @param coordinate
     * @return
     */
    public int getMinValue(int coordinate) {
        return Math.max(coordinate - 1, 0);
    }

    /**
     * this method is used to compare the Grids objects by width, height and hashmaps.
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grid grid = (Grid) o;
        return width == grid.width &&
                height == grid.height &&
                Objects.equals(cells, grid.cells);
    }

    /**
     * this method is overridden to be able to compare properly the objects.
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(cells, width, height);
    }
}
