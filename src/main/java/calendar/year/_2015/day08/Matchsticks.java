package calendar.year._2015.day08;

import utils.Exercise;
import utils.enums.Part;
import utils.fileparser.Parser;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;

public class Matchsticks extends Exercise {
    public Matchsticks(LocalDate date) {
        super(date);
    }

    private static final Pattern ENCLOSING_QUOTES = Pattern.compile("^\"|\"$");
    private static final Pattern ESCAPED_SINGLE_CHAR = Pattern.compile("\\\\[\\\"\\\\]");
    private static final Pattern ESCAPED_HEXA_CODE = Pattern.compile("\\\\x[\\dabcdef]{2}");

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<String> inputLines = Parser.parseLines(getInputPath(testMode));

        Function<String, Integer> deltaComputationFunction = Part.PART_1 == part ?
                this::computeNbOfDiscountedCharacters :
                this::computeNbOfNewlyNecessariesCharacters;
        int toBeDiscountedChars = inputLines.stream()
                .map(deltaComputationFunction)
                .reduce(0, Integer::sum);

        return print(toBeDiscountedChars);
    }

    /**
     * @param input The string input to analyze
     * @return the number of characters that is lost during string interpolation
     */
    private int computeNbOfDiscountedCharacters(String input) {
        Map<Pattern, Integer> nbDiscountedCharPerPattern = Map.of(
                ENCLOSING_QUOTES, 1,
                ESCAPED_SINGLE_CHAR, 1,
                ESCAPED_HEXA_CODE, 3
        );

        return nbDiscountedCharPerPattern.entrySet().stream()
                .map(entry -> entry.getValue() * Math.toIntExact(entry.getKey().matcher(input).results().count()))
                .reduce(0, Integer::sum);
    }

    /**
     * @param input The string input to analyze
     * @return the number of new characters that is needed for string interpolation
     */
    private int computeNbOfNewlyNecessariesCharacters(String input) {
        Map<Pattern, Integer> nbDiscountedCharPerPattern = Map.of(
                ENCLOSING_QUOTES, 2,
                ESCAPED_SINGLE_CHAR, 2,
                ESCAPED_HEXA_CODE, 1
        );

        return nbDiscountedCharPerPattern.entrySet().stream()
                .map(entry -> entry.getValue() * Math.toIntExact(entry.getKey().matcher(input).results().count()))
                .reduce(0, Integer::sum);
    }
}
