import numberartparser.cliinterface.CliInterface;
import org.junit.jupiter.api.Test;

public class RenderTest {
    @Test
    void systemTest() {
        String[] arguments = {"src/test/testResources/questions/input_Q1b.txt"};
        CliInterface.main(arguments);
    }
}
