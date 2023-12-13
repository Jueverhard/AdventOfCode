package calendar.year._2023.day13;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class PointOfIncidence extends Exercise {

    public PointOfIncidence(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<AshPattern> patterns = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            List<List<Character>> patternLines = new ArrayList<>();
            while (null != (line = br.readLine())) {
                if (line.isBlank()) {
                    patterns.add(new AshPattern(List.copyOf(patternLines)));
                    patternLines.clear();
                } else {
                    patternLines.add(line.chars()
                            .mapToObj(intChar -> (char) intChar)
                            .toList());
                }
            }
        }

        int horizontalNbLines = patterns.stream()
                .map(AshPattern::summarizePatternHorizontally)
                .filter(OptionalInt::isPresent)
                .map(OptionalInt::getAsInt)
                .reduce(0, Integer::sum);

        int verticalNbLines = patterns.stream()
                .map(AshPattern::summarizePatternVertically)
                .filter(OptionalInt::isPresent)
                .map(OptionalInt::getAsInt)
                .reduce(0, Integer::sum);

        return print(horizontalNbLines * 100 + verticalNbLines);
    }
}
