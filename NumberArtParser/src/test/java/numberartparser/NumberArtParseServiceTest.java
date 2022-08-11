package numberartparser;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Executable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NumberArtParseServiceTest {

    public static final String TEST_INPUT_1 = "src/test/testResources/questions/input_Q1a.txt";
    public static final String TEST_OUTPUT_1 = "src/test/testResources/answers/output_Q1a.txt";
    public static final String TEST_INPUT_2 = "src/test/testResources/questions/input_Q1b.txt";
    public static final String TEST_OUTPUT_2= "src/test/testResources/answers/output_Q1b.txt";

    @Test
    void testFirstDataset(){
        testSetFromFile(TEST_INPUT_1, TEST_OUTPUT_1, true);
    }

    @Test
    void testSecondDataset(){
        testSetFromFile(TEST_INPUT_2, TEST_OUTPUT_2, true);
    }

    @Test
    void testFaultyDataset(){
        testSetFromFile(TEST_INPUT_1, TEST_OUTPUT_2, false);
    }

    void testSetFromFile(String inputPath, String outputPath, boolean success) {
        NumberArtParseService numberArtParseService = new NumberArtParseService();
        Path path = Paths.get(outputPath).toAbsolutePath();
        try (
            BufferedReader expectedOutput = new BufferedReader(new FileReader(path.toFile()))) {
            List<String> output = numberArtParseService.readFile(inputPath);
            for (String outputIterator : output) {
                if(success) {
                    assertEquals(expectedOutput.readLine(), outputIterator);
                } else
                    assertNotEquals(expectedOutput.readLine(), outputIterator);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}