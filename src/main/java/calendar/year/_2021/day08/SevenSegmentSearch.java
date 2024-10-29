package calendar.year._2021.day08;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static calendar.year._2021.day08.Digit.UNIQUE_SEGMENT_SIZED_DIGIT;

public class SevenSegmentSearch extends Exercise {
    public SevenSegmentSearch(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        // Data initialization
        List<Display> displays = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            while (null != (line = br.readLine())) {
                String[] inputParts = line.split(" \\| ");
                List<Signal> patterns = parseSignals(inputParts[0]);
                List<Signal> outputs = parseSignals(inputParts[1]);

                displays.add(new Display(patterns, outputs));
            }
        }

        int res = Part.PART_1 == part ?
                countIdentifiableDigits(displays) :
                computeDisplaysSum(displays);

        return print(res);
    }

    /**
     * Parses a string representing multiple signals and converts each signal into a Signal object.
     *
     * @param signals the input string containing signal patterns separated by whitespace.
     * @return the resulting signals list.
     */
    private List<Signal> parseSignals(String signals) {
        return Pattern.compile("\\w+").matcher(signals).results()
                .map(MatchResult::group)
                .map(segments -> new Signal(segments.chars()
                        .mapToObj(c -> (char) c)
                        .collect(Collectors.toSet())
                ))
                .toList();
    }

    /**
     * Counts the number of output signals in the given displays that can be identified
     * uniquely by their number of segments.
     *
     * @param displays the list of Display objects containing input patterns and output signals.
     * @return the count of output signals that have a unique number of segments.
     */
    private int countIdentifiableDigits(List<Display> displays) {
        // Extract the number of segments that uniquely identify a digit
        Set<Integer> uniqueDigitSegmentsSize = UNIQUE_SEGMENT_SIZED_DIGIT.stream()
                .map(Digit::getNbSegments)
                .collect(Collectors.toSet());

        return (int) displays.stream()
                .flatMap(display -> display.outputs().stream())
                .filter(signal -> uniqueDigitSegmentsSize.contains(signal.length()))
                .count();
    }

    /**
     * Computes the sum of the values of all the given display objects.
     *
     * @param displays the list of Display objects whose values are to be summed.
     * @return the sum of the values of all display objects.
     */
    private int computeDisplaysSum(List<Display> displays) {
        return displays.stream()
                .map(Display::computeValue)
                .reduce(Integer::sum)
                .orElseThrow();
    }
}
