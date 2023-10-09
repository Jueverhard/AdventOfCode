package calendar.year._2016.day06;

import utils.Exercise;
import utils.enums.Part;
import utils.fileparser.Parser;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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

        // Choose whether to keep most or least current character according to the exercise part
        Function<Stream<Entry<Character, Long>>, Character> charExtractor = Part.PART_1 == part ?
                stream -> stream.max(Map.Entry.comparingByValue()).orElseThrow().getKey() :
                stream -> stream.min(Map.Entry.comparingByValue()).orElseThrow().getKey();

        // For every character position of the messages
        String result = IntStream.range(0, messages.get(0).length())
                // Find the appropriate character based on frequency
                .mapToObj(i -> charExtractor.apply(
                                        messages.stream()
                                                .map(message -> message.charAt(i))
                                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                                                .entrySet().stream()
                                )
                )
                // Concat every character into the final message
                .map(String::valueOf)
                .reduce(String::concat)
                .orElseThrow();

        return print(result);
    }
}
