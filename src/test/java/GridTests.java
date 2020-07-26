import org.junit.Test;

import java.util.HashMap;
import static org.junit.Assert.assertEquals;

public class GridTests {
    Grid grid = new Grid(new HashMap<Coordinate, Color>(), 1, 1);

    @Test
    public void getMaxValue_onTwoDifferentInputs_shouldReturnSmallerPlusOne() {
        int result = this.grid.getMaxValue(50, 60);
        assertEquals(result, 51);
    }

    @Test
    public void getMinValue_onInputZero_shouldReturnZero() {
        int result = this.grid.getMinValue(0);
        assertEquals(result, 0);
    }

    @Test
    public void getMinValue_onInputBiggerThanZero_shouldReturnInputMinusOne() {
        int result = this.grid.getMinValue(5);
        assertEquals(result, 4);
    }
}
