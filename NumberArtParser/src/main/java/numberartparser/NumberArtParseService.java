package numberartparser;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NumberArtParseService {

    public static final String ILLEGAL = " ILLEGAL";

    public List<String> readFile(String fileName) {
        List<String> invoiceLines = new ArrayList<>();
        Path path = Paths.get(fileName).toAbsolutePath();
        try (BufferedReader input = new BufferedReader(new FileReader(path.toFile()))) {
            String firstLine = input.readLine();
            String secondLine = input.readLine();
            String thirdLine = input.readLine();
            String emptyLine = input.readLine();
            while (emptyLine != null) {
                StringBuilder invoiceLine = decodeInvoiceLine(firstLine, secondLine, thirdLine);
                invoiceLines.add(invoiceLine.toString());

                firstLine = input.readLine();
                secondLine = input.readLine();
                thirdLine = input.readLine();
                emptyLine = input.readLine();
            }
        } catch (IOException e) {
            System.err.println("File not found: " + path);
        }
        return invoiceLines;
    }

    private StringBuilder decodeInvoiceLine(String firstLine, String secondLine, String thirdLine) {
        char[] firstLineChar = firstLine.toCharArray();
        char[] secondLineChar = secondLine.toCharArray();
        char[] thirdLineChar = thirdLine.toCharArray();
        boolean illegal = false;
        StringBuilder invoiceLine = new StringBuilder();
        for (int i = 0; i < firstLineChar.length; i += 3) {
            Optional<NumberRepresentations> byCharArt = NumberRepresentations.getByCharArt(
                    new char[][]{getCharArray(firstLineChar, i),
                            getCharArray(secondLineChar, i), getCharArray(thirdLineChar, i)});
            invoiceLine.append(byCharArt.isPresent() ? byCharArt.get().getValue() : "?");
            if(!byCharArt.isPresent()) {
                illegal = true;
            }
        }
        if(illegal) {
            invoiceLine.append(ILLEGAL);
        }
        return invoiceLine;
    }

    private char[] getCharArray(char[] firstLineChar, int i) {
        return new char[]{firstLineChar[i], firstLineChar[i + 1], firstLineChar[i + 2]};
    }

    public void writeFile(List<String> decodedLines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            for(String line : decodedLines) {
                writer.write(line + "\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
