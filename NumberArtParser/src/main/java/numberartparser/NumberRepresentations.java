package numberartparser;

import java.util.Arrays;
import java.util.Optional;

public enum NumberRepresentations {
    ZERO(0, new char[][]{
            {' ', '_', ' '},
            {'|', ' ', '|'},
            {'|', '_', '|'},
    }),
    ONE(1, new char[][]{
            {' ', ' ', ' '},
            {' ', ' ', '|'},
            {' ', ' ', '|'},
    }),
    TWO(2, new char[][]{
            {' ', '_', ' '},
            {' ', '_', '|'},
            {'|', '_', ' '},
    }),
    THREE(3, new char[][]{
            {' ', '_', ' '},
            {' ', '_', '|'},
            {' ', '_', '|'},
    }),
    FOUR(4, new char[][]{
            {' ', ' ', ' '},
            {'|', '_', '|'},
            {' ', ' ', '|'},
    }),
    FIVE(5, new char[][]{
            {' ', '_', ' '},
            {'|', '_', ' '},
            {' ', '_', '|'},
    }),
    SIX(6, new char[][]{
            {' ', '_', ' '},
            {'|', '_', ' '},
            {'|', '_', '|'},
    }),
    SEVEN(7, new char[][]{
            {' ', '_', ' '},
            {' ', ' ', '|'},
            {' ', ' ', '|'},
    }),
    EIGHT(8, new char[][]{
            {' ', '_', ' '},
            {'|', '_', '|'},
            {'|', '_', '|'},
    }),
    NINE(9, new char[][]{
            {' ', '_', ' '},
            {'|', '_', '|'},
            {' ', '_', '|'},
    });

    private int value;

    private char[][] numberArt;

    NumberRepresentations(int value, char[][] numberArt) {
        this.value = value;
        this.numberArt = numberArt;
    }

    public static Optional<NumberRepresentations> getByCharArt(char[][] numberArt) {
        return Arrays.stream(NumberRepresentations.values()).filter(
                representation -> Arrays.deepEquals(representation.getNumberArt(), numberArt))
                .findFirst();
    }

    public int getValue() {
        return value;
    }

    public char[][] getNumberArt() {
        return numberArt;
    }
}
