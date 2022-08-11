package numberartparser.cliinterface;

import numberartparser.NumberArtParseService;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.List;
import java.util.concurrent.Callable;

@Command(name = "generate", mixinStandardHelpOptions = true,
        description = "Reads the ASCII art of invoice numbers, and decode it into normal numbers")
public class CliInterface implements Callable<Integer> {
    @Parameters(index = "0", description = "The txt resource file")
    private String path;

    @Override
    public Integer call() throws Exception { // your business logic goes here...
        NumberArtParseService numberArtParseService= new NumberArtParseService();
        List<String> decodedLines = numberArtParseService.readFile(path);
        numberArtParseService.writeFile(decodedLines);
        return 0;
    }

    // this example implements Callable, so parsing, error handling and handling user
    // requests for usage help or version help can be done with one line of code.
    public static void main(String... args) {
        int exitCode = new CommandLine(new CliInterface()).execute(args);
        System.exit(exitCode);
    }
}
