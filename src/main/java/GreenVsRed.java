import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This is a game called Green vs. Red, where 2D grid in theory can be infinite, but in our case it is x<=y<1000.
 * Each cell on this grid can be either green(1) or red(0). The initial state of the generation or "Generation Zero"
 * is received from the console. After some simple rules the new generation is created, but the important thing is
 * that all the 4 rules apply at the same time for the whole grid in order for the next generation to be formed.
 * After all the generations are created, the count of which is also given from the console we have to count how
 * many times a specific cell given by coordinate from the last line of input have been green. The result is
 * printed on the console.
 *
 * Unit tests are made for all the methods.
 */
public class GreenVsRed {
    public static void main(String[] args) throws IOException {
        new Generations(new ConsoleInput(new InputStreamReader(System.in))).start();
    }
}
