import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * This class is used to read all the console input and store it in fields, which
 * later can be called from the other classes.
 *
 */
public class ConsoleInput {
    private final int width;
    private final int height;
    private final List<String> inputsForZeroGeneration = new ArrayList<>();
    private final Coordinate inspectCoordinate;
    private final int numberOfGenerations;
    private final BufferedReader bufferedReader;

    /**
     * All the logic in this class is in the constructor, because i wanted to
     * have the input right after the program start.
     *
     * @param inputStreamReader
     * @throws IOException
     */
    public ConsoleInput(InputStreamReader inputStreamReader) throws IOException {
        this.bufferedReader = new BufferedReader(inputStreamReader);

        // taking the dimensions in int array.
        int[] dimensions = getStringFromConsoleAndSplitByCommaAndSpace(this.bufferedReader.readLine());
        this.width = dimensions[0];
        this.height = dimensions[1];

        // reading the input for the generation zero
        for (int i = 0; i < height; i++) {
            inputsForZeroGeneration.add(this.bufferedReader.readLine());
        }

        // taking the input for inspected cell and number of generations
        int[] taskInput = getStringFromConsoleAndSplitByCommaAndSpace(this.bufferedReader.readLine());

        int inspectX = taskInput[0];
        int inspectY = taskInput[1];

        this.inspectCoordinate = new Coordinate(inspectX, inspectY);
        this.numberOfGenerations = taskInput[2];
    }


    /**
     *
     * this method gets a string which have to be ints with comma and space between them
     * and separates them by comma and space.
     *
     * @param s
     * @return
     */
    public int[] getStringFromConsoleAndSplitByCommaAndSpace(String s) {

        return Arrays
                .stream(s.split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<String> getInputsForZeroGeneration() {
        return inputsForZeroGeneration;
    }

    public Coordinate getInspectCoordinate() {
        return inspectCoordinate;
    }

    public int getNumberOfGenerations() {
        return numberOfGenerations;
    }
}
