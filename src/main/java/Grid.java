import java.util.HashMap;
import java.util.Objects;

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

    //todo unit test
    public int getMaxValue(int coordinate, int maxValue) {
        return Math.min(coordinate + 1, maxValue - 1);
    }

    //todo unit test
    public int getMinValue(int coordinate) {
        return Math.max(coordinate - 1, 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grid grid = (Grid) o;
        return width == grid.width &&
                height == grid.height &&
                Objects.equals(cells, grid.cells);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cells, width, height);
    }
}
