package calendar.year._2023.day13;

import org.apache.commons.lang3.tuple.Pair;
import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;

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

        int result = Part.PART_1 == part ?
                exercise1(patterns) :
                exercise2(patterns);
        return print(result);
    }

    private int exercise1(List<AshPattern> patterns) {
        int horizontalNbLines = patterns.stream()
                .map(AshPattern::summarizePatternHorizontally)
                .filter(Predicate.not(Set::isEmpty))
                .map(Collections::min)
                .reduce(0, Integer::sum);

        int verticalNbLines = patterns.stream()
                .map(AshPattern::summarizePatternVertically)
                .filter(Predicate.not(Set::isEmpty))
                .map(Collections::min)
                .reduce(0, Integer::sum);

        return horizontalNbLines * 100 + verticalNbLines;
    }

    private int exercise2(List<AshPattern> patterns) {
        List<Integer> horizontals = new ArrayList<>();
        List<Integer> verticals = new ArrayList<>();
        record ReflexionIndex(int index, int patternNumber) {}

        for (AshPattern pattern : patterns) {
            final Set<Integer> initialHorizontals = pattern.summarizePatternHorizontally();
            final Set<Integer> initialVerticals = pattern.summarizePatternVertically();

            List<AshPattern> alternativePatterns = pattern.generateAlternativePatterns();
            Optional<ReflexionIndex> horizontal = IntStream.range(0, alternativePatterns.size())
                    .mapToObj(i -> Pair.of(i, alternativePatterns.get(i).summarizePatternHorizontally()))
                    .filter(alternativeSynthesis -> !alternativeSynthesis.getRight().isEmpty() && !initialHorizontals.containsAll(alternativeSynthesis.getRight()))
                    .map(alternativeSynthesis -> new ReflexionIndex(
                            alternativeSynthesis.getLeft(),
                            alternativeSynthesis.getRight().stream()
                                    .filter(index -> !initialHorizontals.contains(index))
                                    .findFirst().orElseThrow()
                    ))
                    .min(Comparator.comparingInt(ReflexionIndex::index));

            if (horizontal.isPresent()) {
                horizontals.add(horizontal.get().patternNumber());
            } else {
                Optional<ReflexionIndex> vertical = IntStream.range(0, alternativePatterns.size())
                        .mapToObj(i -> Pair.of(i, alternativePatterns.get(i).summarizePatternVertically()))
                        .filter(alternativeSynthesis -> !alternativeSynthesis.getRight().isEmpty() && !initialVerticals.containsAll(alternativeSynthesis.getRight()))
                        .map(alternativeSynthesis -> new ReflexionIndex(
                                alternativeSynthesis.getLeft(),
                                alternativeSynthesis.getRight().stream()
                                        .filter(index -> !initialVerticals.contains(index))
                                        .findFirst().orElseThrow()
                        ))
                        .min(Comparator.comparingInt(ReflexionIndex::index));

                vertical.ifPresent(value -> verticals.add(value.patternNumber()));
            }
        }

        int horizontalNbLines = horizontals.stream()
                .reduce(0, Integer::sum);
        int verticalNbLines = verticals.stream()
                .reduce(0, Integer::sum);

        return horizontalNbLines * 100 + verticalNbLines;
    }
}
