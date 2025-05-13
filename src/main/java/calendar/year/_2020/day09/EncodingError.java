package calendar.year._2020.day09;

import utils.Exercise;
import utils.enums.Part;
import utils.fileparser.Parser;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EncodingError extends Exercise {

    public EncodingError(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        // Data initialization
        List<Long> numbers = Parser.parseLines(this.getInputPath(testMode)).stream()
                .map(Long::parseLong)
                .toList();
        int range = testMode ? 5 : 25;

        long result = IntStream.range(range, numbers.size())
                .filter(i -> !isSumIncluded(numbers.get(i), numbers.subList(i - range, i)))
                .mapToLong(numbers::get)
                .findFirst()
                .orElseThrow();

        return print(result);
    }

    private boolean isSumIncluded(long expected, List<Long> numbers) {
        return IntStream.range(0, numbers.size())
                .mapToObj(i -> IntStream.range(0, numbers.size())
                        .filter(j -> j != i)
                        .mapToLong(j -> numbers.get(i) + numbers.get(j))
                        .boxed()
                        .collect(Collectors.toSet())
                )
                .flatMap(Set::stream)
                .anyMatch(sum -> sum == expected);
    }
}
