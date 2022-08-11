package numberartparser;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class NumberRepresentationsTest {
    @Test
    void testZeroAssertion() {
        recognitionAssertion(0, new char[][]{
                {' ', '_', ' '},
                {'|', ' ', '|'},
                {'|', '_', '|'},
        });
    }

    @Test
    void testOneAssertion() {
        recognitionAssertion(1, new char[][]{
                {' ', ' ', ' '},
                {' ', ' ', '|'},
                {' ', ' ', '|'},
        });
    }

    @Test
    void testTwoAssertion() {
        recognitionAssertion(2, new char[][]{
                {' ', '_', ' '},
                {' ', '_', '|'},
                {'|', '_', ' '},
        });
    }

    @Test
    void testThreeAssertion() {
        recognitionAssertion(3, new char[][]{
                {' ', '_', ' '},
                {' ', '_', '|'},
                {' ', '_', '|'},
        });
    }

    @Test
    void testFourAssertion() {
        recognitionAssertion(4, new char[][]{
                {' ', ' ', ' '},
                {'|', '_', '|'},
                {' ', ' ', '|'},
        });
    }

    @Test
    void testFiveAssertion() {
        recognitionAssertion(5, new char[][]{
                {' ', '_', ' '},
                {'|', '_', ' '},
                {' ', '_', '|'},
        });
    }

    @Test
    void testSixAssertion() {
        recognitionAssertion(6, new char[][]{
                {' ', '_', ' '},
                {'|', '_', ' '},
                {'|', '_', '|'},
        });
    }

    @Test
    void testSevenAssertion() {
        recognitionAssertion(7, new char[][]{
                {' ', '_', ' '},
                {' ', ' ', '|'},
                {' ', ' ', '|'},
        });
    }

    @Test
    void testEightAssertion() {
        recognitionAssertion(8, new char[][]{
                {' ', '_', ' '},
                {'|', '_', '|'},
                {'|', '_', '|'},
        });
    }

    @Test
    void testNineAssertion() {
        recognitionAssertion(9, new char[][]{
                {' ', '_', ' '},
                {'|', '_', '|'},
                {' ', '_', '|'},
        });
    }

    @Test
    void testFaultyInput() {
        Optional<NumberRepresentations> byCharArt = NumberRepresentations.getByCharArt(new char[][]{
                {' ', '_', ' '},
                {' ', ' ', ' '},
                {' ', '_', '|'},
        });
        assertFalse(byCharArt.isPresent());
    }

    void recognitionAssertion(int expected, char[][] numberArt) {
        Optional<NumberRepresentations> byCharArt = NumberRepresentations.getByCharArt(numberArt);
        assertTrue(byCharArt.isPresent());
        byCharArt.ifPresent(enumValue -> assertEquals(expected, enumValue.getValue()));
    }

}