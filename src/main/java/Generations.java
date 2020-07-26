import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Generations {
    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private List<Grid> generations = new ArrayList<>();

    //todo unit test with examples from the task
    public void start() throws IOException {
        int[] dimensions = getStringFromConsoleAndSplitByCommaAndSpace();

        int width = dimensions[0];
        int height = dimensions[1];

        HashMap<Coordinate, Color> cells = new HashMap<>();

        for (int i = 0; i < height; i++) {

            String nextLine = this.bufferedReader.readLine();
            for (int j = 0; j < width; j++) {

                Color color = getColorFromInput(nextLine.charAt(j));
                cells.put(new Coordinate(j, i), color);
            }
        }
        generations.add(new Grid(cells, width, height));

        int[] taskInput = getStringFromConsoleAndSplitByCommaAndSpace();

        int inspectX = taskInput[0];
        int inspectY = taskInput[1];
        int numberOfGenerations = taskInput[2];

        for (int i = 0; i < numberOfGenerations; i++) {
            generations.add(generate(generations.get(i)));
        }

        Coordinate coordinate = new Coordinate(inspectX, inspectY);

        long result = generations
                .stream()
                .filter(generation -> generation.getCells().get(coordinate) == Color.GREEN).count();

        System.out.println(result);
    }

    //todo unit test
    private Color getColorFromInput(char charValue) {

        int value = Character.getNumericValue(charValue);
        switch (value) {
            case 0:
                return Color.RED;
            case 1:
                return Color.GREEN;
            default:
                throw new InvalidParameterException("Wrong input value! Accepted values are only 0s and 1s!");
        }
    }

    //todo unit test
    private int[] getStringFromConsoleAndSplitByCommaAndSpace() throws IOException {

        return Arrays
                .stream(this.bufferedReader.readLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    //todo unit test
    private Grid generate(Grid lastGrid) {

        // making shallow and not deep copy since each generation has the same coordinates and they cannot be modified.
        HashMap<Coordinate, Color> cells = (HashMap<Coordinate, Color>) lastGrid.getCells().clone();
        int height = lastGrid.getHeight();
        int width = lastGrid.getWidth();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                int minYValue = lastGrid.getMinValue(i);
                int maxYValue = lastGrid.getMaxValue(i, height);

                int minXValue = lastGrid.getMinValue(j);
                int maxXValue = lastGrid.getMaxValue(j, width);

                int countGreenCells = 0;
                for (int k = minYValue; k <= maxYValue; k++) {
                    for (int l = minXValue; l <= maxXValue; l++) {
                        if (i == k && j == l) {
                            continue;
                        }

                        Coordinate coordinate = new Coordinate(l, k);
                        if (lastGrid.getCells().get(coordinate) == Color.GREEN) {
                            countGreenCells++;
                        }
                    }
                }

                Coordinate inspectCoordinate = new Coordinate(j, i);
                if (cells.get(inspectCoordinate) == Color.RED) {
                    switch (countGreenCells) {
                        case 3:
                        case 6:
                            cells.put(inspectCoordinate, Color.GREEN);
                            break;
                    }
                } else {
                    switch (countGreenCells) {
                        case 0:
                        case 1:
                        case 4:
                        case 5:
                        case 7:
                        case 8:
                            cells.put(inspectCoordinate, Color.RED);
                            break;
                    }
                }
            }
        }
        return new Grid(cells, width, height);
    }
}
