package calendar.year._2020.day09;

import utils.Exercise;
import utils.enums.Part;
import utils.fileparser.Parser;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
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

        // Invalid number computation
        long invalidNumber = IntStream.range(range, numbers.size())
                .filter(i -> !isSumIncluded(numbers.get(i), numbers.subList(i - range, i)))
                .mapToLong(numbers::get)
                .findFirst()
                .orElseThrow();

        long result;
        if (Part.PART_1 == part) {
            result = invalidNumber;
        } else {
            List<Long> contiguousNumbers = findContiguousNumbersSummingTo(
                    numbers.stream().filter(number -> invalidNumber != number).toList(),
                    invalidNumber
            );
            result = Collections.min(contiguousNumbers) + Collections.max(contiguousNumbers);
        }

        return print(result);
    }

    /**
     * @param expected The expected sum.
     * @param numbers  The summable numbers.
     * @return Whether a two numbers sum matches the expected value.
     */
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

    /**
     * @param numbers  The summable numbers.
     * @param expected The expected sum.
     * @return The contiguous set of numbers, if any, that is summable to the expected value.
     */
    private List<Long> findContiguousNumbersSummingTo(List<Long> numbers, long expected) {
        return IntStream.range(0, numbers.size() - 2)
                .mapToObj(start -> IntStream.range(start + 2, numbers.size())
                        .mapToObj(end -> numbers.subList(start, end))
                        .toList()
                ).flatMap(List::stream)
                .filter(contiguousNumbers -> expected == contiguousNumbers.stream().reduce(Long::sum).orElseThrow())
                .findFirst().orElseThrow();
    }
}
