import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;

public class ConsoleInputTests {

    @Test
    public void splitStringByCommaAndSpace_onInput_shouldWorkProperly() throws IOException {
        String data = "3, 3\n" +
                "000\n" +
                "111\n" +
                "000\n" +
                "1, 0, 10\n";
        InputStream is = new ByteArrayInputStream(data.getBytes());
        InputStreamReader reader = new InputStreamReader(is);
        ConsoleInput consoleInput = new ConsoleInput(reader);

        int[] result = new int[]{4, 2};

        assertEquals(consoleInput.getStringFromConsoleAndSplitByCommaAndSpace("4, 2")[0], result[0]);
        assertEquals(consoleInput.getStringFromConsoleAndSplitByCommaAndSpace("4, 2")[1], result[1]);
    }
}
