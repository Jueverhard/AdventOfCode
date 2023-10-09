package calendar.year._2016.day06;

import utils.Exercise;
import utils.enums.Part;
import utils.fileparser.Parser;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SignalsAndNoise extends Exercise {

    public SignalsAndNoise(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        // Fetch all messages
        List<String> messages = Parser.parseLines(getInputPath(testMode));

        // For every character position of the messages
        String result = IntStream.range(0, messages.get(0).length())
                // Find the most frequent character
                .mapToObj(i -> extractCharacterBasedOnFrequency(messages, i, part))
                // Concat every character into the final message
                .map(String::valueOf)
                .reduce(String::concat)
                .orElseThrow();

        return print(result);
    }

    /**
     * Extract a single character from a list of messages, based on a given index position and a strategy defined by the
     * current exercise part (most or least frequent)
     *
     * @param messages Messages to parse
     * @param index    Index required
     * @param part     Exercise part
     * @return The resulting character
     */
    private char extractCharacterBasedOnFrequency(List<String> messages, int index, Part part) {
        // Choose whether to keep most or least current character according to the exercise part
        Function<Stream<Map.Entry<Character, Long>>, Optional<Map.Entry<Character, Long>>> comparator = Part.PART_1 == part ?
                stream -> stream.max(Map.Entry.comparingByValue()) :
                stream -> stream.min(Map.Entry.comparingByValue());

        return comparator.apply(
                        messages.stream()
                                .map(message -> message.charAt(index))
                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                                .entrySet().stream()
                )
                .orElseThrow()
                .getKey();
    }
}
