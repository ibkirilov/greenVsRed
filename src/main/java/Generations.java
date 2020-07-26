import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class is used to start the application and generate new generations
 * just from given latest one. All of them are stored in a List, which later
 * will be examined for the inspected cell.
 */
public class Generations {
    // creating list of grids, which will be the our generations "repository".
    private List<Grid> generations = new ArrayList<>();
    private ConsoleInput consoleInput;

    public Generations() {
    }

    public Generations(ConsoleInput consoleInput) {
       this.consoleInput = consoleInput;
    }

    /**
     * This method is used to create the Zero generation and delegate to another method
     * to create new generations. Counting the number of times when inspected cell has
     * been green and print the result to the console.
     *
     * @return
     * @throws IOException
     */
    public long start() throws IOException {

        // using ConsoleInput.class to read from the console, after which we only get the fields of it.
        int width = this.consoleInput.getWidth();
        int height = this.consoleInput.getHeight();

        // creating cells hashmap, where the cells of the Zero generation's grid will be stored.
        HashMap<Coordinate, Color> cells = new HashMap<>();

        // filling the zero generation by console input.
        for (int i = 0; i < height; i++) {
            // using ConsoleInput.class for inputs for the zero generation.
            String nextLine = this.consoleInput.getInputsForZeroGeneration().get(i);

            for (int j = 0; j < width; j++) {
                // Every cell from the grid is represented by coordinate, which is x and y and color.
                Color color = getColorFromInput(nextLine.charAt(j));

                // putting the result in the hashmap.
                cells.put(new Coordinate(j, i), color);
            }
        }

        // adding zero generation's grid to the list.
        generations.add(new Grid(cells, width, height));

        // using for loop to create all generations and add them to the list of grids.
        for (int i = 0; i < consoleInput.getNumberOfGenerations(); i++) {
            generations.add(generate(generations.get(i)));
        }

        // using long for the result because of the .count method in hashmap.
        // taking the generations list, streaming all of the elements and then
        // filtering them by the color of a specific cell.
        long result = generations
                .stream()
                .filter(generation -> generation.getCells().get(consoleInput.getInspectCoordinate()) == Color.GREEN).count();

        // printing the result
        System.out.println(result);
        return result;
    }

    /**
     * In this method on given char, 1s or 0s only, it returns object Color
     * with the color, which is given from the assignment. 0 is Color.RED and
     * 1 is Color.GREEN
     *
     * @param charValue
     * @return
     */
    public Color getColorFromInput(char charValue) {

        // if another character is entered it will throw an exception with message.
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

    /**
     * In this method the last made grid is given and after some operations
     * it returns new grid with the rules from assignment observed.
     *
     * @param lastGrid
     * @return
     */
    public Grid generate(Grid lastGrid) {

        // making shallow and not deep copy since each generation has the same coordinates and they cannot be modified.
        HashMap<Coordinate, Color> cells = (HashMap<Coordinate, Color>) lastGrid.getCells().clone();
        int height = lastGrid.getHeight();
        int width = lastGrid.getWidth();

        // looping all over the grid to check for every single cell.
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                // we need max and min values for the coordinate to examine how many green cells we have around the examined cell.
                int minYValue = lastGrid.getMinValue(i);
                int maxYValue = lastGrid.getMaxValue(i, height);

                int minXValue = lastGrid.getMinValue(j);
                int maxXValue = lastGrid.getMaxValue(j, width);

                int countGreenCells = 0;

                // looping in the small grid, which is around the examined cell.
                for (int k = minYValue; k <= maxYValue; k++) {
                    for (int l = minXValue; l <= maxXValue; l++) {

                        // the examined cell is not included in the party and the loop continues.
                        if (i == k && j == l) {
                            continue;
                        }

                        // creating new coordinate, which will be used to check the color of the cell
                        Coordinate coordinate = new Coordinate(l, k);
                        if (lastGrid.getCells().get(coordinate) == Color.GREEN) {
                            countGreenCells++;
                        }
                    }
                }

                // creating coordinate for the current inspected cell
                Coordinate inspectCoordinate = new Coordinate(j, i);

                // checking the color of the inspected cell to know which rule will be used here
                if (lastGrid.getCells().get(inspectCoordinate) == Color.RED) {
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
