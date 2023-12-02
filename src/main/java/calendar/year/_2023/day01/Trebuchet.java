package calendar.year._2023.day01;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Trebuchet extends Exercise {

    public Trebuchet(LocalDate date) {
        super(date);
    }

    private static final Map<String, Integer> STRING_NUMBER_VALUE = Map.of(
            "one", 1,
            "two", 2,
            "three", 3,
            "four", 4,
            "five", 5,
            "six", 6,
            "seven", 7,
            "eight", 8,
            "nine", 9
    );

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        int total = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            Pattern pattern = Part.PART_1 == part ?
                    Pattern.compile("\\d") :
                    Pattern.compile("\\d|(?=(one)|(two)|(three)|(four)|(five)|(six)|(seven)|(eight)|(nine))");

            while (null != (line = br.readLine())) {
                Matcher matcher = pattern.matcher(line);
                List<Integer> digits = extractDigits(matcher);
                total += 10 * digits.get(0) + digits.get(digits.size() - 1);
            }
        }

        return print(total);
    }

    private List<Integer> extractDigits(Matcher matcher) {
        List<Integer> digits = new ArrayList<>();

        while (matcher.find()) {
            String stringNumber = matcher.group();
            int number = stringNumber.isBlank() ?
                    IntStream.rangeClosed(1, matcher.groupCount())
                            .mapToObj(matcher::group)
                            .filter(Objects::nonNull)
                            .map(STRING_NUMBER_VALUE::get)
                            .findFirst().orElseThrow() :
                    Integer.parseInt(stringNumber);

            digits.add(number);
        }

        return digits;
    }
}
