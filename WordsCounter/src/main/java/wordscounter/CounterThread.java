package wordscounter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class CounterThread implements Runnable {

    private WordsCounter wordsCounter;

    private String inputPath;

    public CounterThread(String inputPath, WordsCounter wordsToCounter) {
        this.wordsCounter = wordsToCounter;
        this.inputPath = inputPath;
    }

    public void run() {
        Path path = Paths.get(inputPath).toAbsolutePath();
        try (BufferedReader input = new BufferedReader(new FileReader(path.toFile()))) {
            input.lines()
                    .map(line -> line.split(" "))
                    .forEach(line -> Arrays.stream(line)
                            .forEach(word -> {
                                wordsCounter.increaseValue(word);
                            }));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
