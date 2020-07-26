import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class GenerationsTests {
    private Generations generations;

    public void initializeGenerations(String data) throws IOException {
        InputStream is = new ByteArrayInputStream(data.getBytes());
        InputStreamReader reader = new InputStreamReader(is);
        this.generations = new Generations(new ConsoleInput(reader));
    }

    @Test
    public void getColorFromInput_check0forInput_shouldReturnRed() {
        this.generations = new Generations();
        Color color = generations.getColorFromInput('0');
        assertEquals(color, Color.RED);
    }

    @Test
    public void getColorFromInput_check1forInput_shouldReturnGreen() {
        this.generations = new Generations();
        Color color = generations.getColorFromInput('1');
        assertEquals(color, Color.GREEN);
    }

    @Test
    public void generate_onGivenGrid_shouldReturnNewGridWithChangedCells() {
        this.generations = new Generations();
        HashMap<Coordinate, Color> cell1 = new HashMap<>();
        cell1.put(new Coordinate(0, 0), Color.GREEN);
        cell1.put(new Coordinate(1, 0), Color.RED);
        cell1.put(new Coordinate(2, 0), Color.GREEN);
        cell1.put(new Coordinate(0, 1), Color.RED);
        cell1.put(new Coordinate(1, 1), Color.GREEN);
        cell1.put(new Coordinate(2, 1), Color.RED);

        Grid grid = generations.generate(new Grid(cell1, 3, 2));

        HashMap<Coordinate, Color> cell2 = new HashMap<>();
        cell2.put(new Coordinate(0, 0), Color.RED);
        cell2.put(new Coordinate(1, 0), Color.GREEN);
        cell2.put(new Coordinate(2, 0), Color.RED);
        cell2.put(new Coordinate(0, 1), Color.RED);
        cell2.put(new Coordinate(1, 1), Color.GREEN);
        cell2.put(new Coordinate(2, 1), Color.RED);

        Grid newGrid = new Grid(cell2, 3, 2);

        assertEquals(newGrid, grid);
    }

    @Test
    public void testGenerate_withExampleOne_shouldReturn5() throws IOException {
        String data = "3, 3\n" +
                "000\n" +
                "111\n" +
                "000\n" +
                "1, 0, 10\n";
        initializeGenerations(data);
        assertEquals(generations.start(), 5L);
    }

    @Test
    public void testGenerate_withExampleTwo_shouldReturn14() throws IOException {
        String data = "4, 4\n" +
                "1001\n" +
                "1111\n" +
                "0100\n" +
                "1010\n" +
                "2, 2, 15\n";
        initializeGenerations(data);
        assertEquals(generations.start(), 14L);
    }
}
