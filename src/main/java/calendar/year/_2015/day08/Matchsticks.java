package calendar.year._2015.day08;

import utils.Exercise;
import utils.enums.Part;
import utils.fileparser.Parser;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Matchsticks extends Exercise {
    public Matchsticks(LocalDate date) {
        super(date);
    }

    private static final Pattern ENCLOSING_QUOTES = Pattern.compile("^\"|\"$");
    private static final Pattern ESCAPED_SINGLE_CHAR = Pattern.compile("\\\\[\\\"\\\\]");
    private static final Pattern ESCAPED_HEXA_CODE = Pattern.compile("\\\\x[\\da-f]{2}");

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<String> inputLines = Parser.parseLines(getInputPath(testMode));

        // Prepare the delta caused in string size for each pattern
        Map<Pattern, Integer> deltaStringSize = Part.PART_1 == part ?
                Map.of(
                        ENCLOSING_QUOTES, 1,
                        ESCAPED_SINGLE_CHAR, 1,
                        ESCAPED_HEXA_CODE, 3
                ) :
                Map.of(
                        ENCLOSING_QUOTES, 2,
                        ESCAPED_SINGLE_CHAR, 2,
                        ESCAPED_HEXA_CODE, 1
                );

        int toBeDiscountedChars = inputLines.stream()
                .map(input -> computeDeltaInStringSize(input, deltaStringSize))
                .reduce(0, Integer::sum);

        return print(toBeDiscountedChars);
    }

    /**
     * @param input                 The string input to analyze
     * @param deltaCausedPerPattern The delta caused in string size per pattern
     * @return the number of characters that is changing in the string after computation
     */
    private int computeDeltaInStringSize(String input, Map<Pattern, Integer> deltaCausedPerPattern) {
        return deltaCausedPerPattern.entrySet().stream()
                .map(entry -> entry.getValue() * Math.toIntExact(entry.getKey().matcher(input).results().count()))
                .reduce(0, Integer::sum);
    }
}
