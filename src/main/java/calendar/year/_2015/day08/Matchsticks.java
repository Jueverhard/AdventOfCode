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

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<String> inputLines = Parser.parseLines(getInputPath(testMode));

        int toBeDiscountedChars = inputLines.stream()
                .map(this::computeNbOfDiscountedCharacters)
                .reduce(0, Integer::sum);

        return print(toBeDiscountedChars);
    }

    /**
     * @param input The string input to analyze
     * @return the number of characters that is lost during string interpolation
     */
    private int computeNbOfDiscountedCharacters(String input) {
        Pattern enclosingQuotes = Pattern.compile("^\"|\"$");
        Pattern escapedSingleChar = Pattern.compile("\\\\[\\\"\\\\]");
        Pattern escapedHexaCode = Pattern.compile("\\\\x[\\dabcdef]{2}");
        Map<Pattern, Integer> nbDiscountedCharPerPattern = Map.of(
                enclosingQuotes, 1,
                escapedSingleChar, 1,
                escapedHexaCode, 3
        );

        return nbDiscountedCharPerPattern.entrySet().stream()
                .map(entry -> entry.getValue() * Math.toIntExact(entry.getKey().matcher(input).results().count()))
                .reduce(0, Integer::sum);
    }
}
