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
            Pattern wordPattern = Pattern.compile("\\w+");
            while (null != (line = br.readLine())) {
                String[] inputParts = line.split(" \\| ");
                List<Signal> patterns = wordPattern.matcher(inputParts[0]).results()
                        .map(MatchResult::group)
                        .map(segments -> new Signal(segments.chars()
                                .mapToObj(c -> (char) c)
                                .collect(Collectors.toSet())
                        ))
                        .toList();
                List<Signal> outputs = wordPattern.matcher(inputParts[1]).results()
                        .map(MatchResult::group)
                        .map(segments -> new Signal(segments.chars()
                                .mapToObj(c -> (char) c)
                                .collect(Collectors.toSet())
                        ))
                        .toList();

                displays.add(new Display(patterns, outputs));
            }
        }

        int res = Part.PART_1 == part ?
                countIdentifiableDigits(displays) :
                computeDisplaysSum(displays);

        return print(res);
    }

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

    private int computeDisplaysSum(List<Display> displays) {
        return displays.stream()
                .map(Display::computeValue)
                .reduce(Integer::sum)
                .orElseThrow();
    }
}
