package calendar.year._2023.day13;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public record AshPattern(List<List<Character>> ashAndRocks) {

    public OptionalInt summarizePatternVertically() {
        int height = ashAndRocks.get(0).size();
        return IntStream.range(1, height)
                .filter(y -> {
                    Pair<AshPattern, AshPattern> subPatterns = splitLeftRight(y);
                    return subPatterns.getLeft().isHorizontallySymmetricTo(subPatterns.getRight());
                })
                .findFirst();
    }

    public OptionalInt summarizePatternHorizontally() {
        int width = ashAndRocks.size();
        return IntStream.range(1, width)
                .filter(x -> {
                    Pair<AshPattern, AshPattern> subPatterns = splitTopDown(x);
                    return subPatterns.getLeft().isVerticallySymmetricTo(subPatterns.getRight());
                })
                .findFirst();
    }

    private boolean isVerticallySymmetricTo(AshPattern pattern) {
        return IntStream.range(0, ashAndRocks.size())
                .allMatch(i -> ashAndRocks.get(i).equals(pattern.ashAndRocks().get(ashAndRocks.size() - 1 - i)));
    }

    private boolean isHorizontallySymmetricTo(AshPattern pattern) {
        return IntStream.range(0, ashAndRocks.size())
                .allMatch(i -> equalsIfReversed(ashAndRocks.get(i), pattern.ashAndRocks().get(i)));
    }

    private boolean equalsIfReversed(List<Character> left, List<Character> right) {
        List<Character> reversedRight = new ArrayList<>(right);
        Collections.reverse(reversedRight);
        return left.equals(reversedRight);
    }

    private Pair<AshPattern, AshPattern> splitLeftRight(int index) {
        SublistSizes sublistSizes = new SublistSizes(ashAndRocks.get(0).size(), index);
        List<Pair<List<Character>, List<Character>>> subPairs = ashAndRocks.stream()
                .map(line -> Pair.of(
                        line.subList(sublistSizes.getFirstPartBeginning(), sublistSizes.getSecondPartBeginning()),
                        line.subList(sublistSizes.getSecondPartBeginning(), sublistSizes.getSecondPartEnding())
                ))
                .toList();

        List<List<Character>> left = subPairs.stream()
                .map(Pair::getLeft)
                .toList();
        List<List<Character>> right = subPairs.stream()
                .map(Pair::getRight)
                .toList();
        return Pair.of(new AshPattern(left), new AshPattern(right));
    }

    private Pair<AshPattern, AshPattern> splitTopDown(int index) {
        SublistSizes sublistSizes = new SublistSizes(ashAndRocks.size(), index);
        List<List<Character>> left = ashAndRocks.subList(
                sublistSizes.getFirstPartBeginning(),
                sublistSizes.getSecondPartBeginning()
        );
        List<List<Character>> right = ashAndRocks.subList(
                sublistSizes.getSecondPartBeginning(),
                sublistSizes.getSecondPartEnding()
        );
        return Pair.of(new AshPattern(left), new AshPattern(right));
    }
}
