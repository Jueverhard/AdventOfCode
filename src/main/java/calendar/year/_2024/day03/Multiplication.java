package calendar.year._2024.day03;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public final class Multiplication extends Operation {

    private final int a;

    private final int b;

    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");

    public Multiplication(String input) {
        super();
        List<Integer> inputParts = NUMBER_PATTERN.matcher(input).results()
                .map(MatchResult::group)
                .map(Integer::parseInt)
                .toList();

        this.a = inputParts.get(0);
        this.b = inputParts.get(1);
    }

    public int compute() {
        return a * b;
    }
}
