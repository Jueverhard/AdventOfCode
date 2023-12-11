package calendar.year._2023.day09;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public record Sequence(List<Integer> numbers) {

    public int computeNextValue() {
        List<Sequence> subSequences = computeSubSequences();

        return subSequences.stream()
                .mapToInt(Sequence::getLastValue)
                .sum();
    }

    public int computePreviousValue() {
        List<Sequence> subSequences = computeSubSequences();

        return IntStream.range(0, subSequences.size())
                .boxed()
                .sorted(Comparator.reverseOrder())
                .map(i -> subSequences.get(i).numbers().get(0))
                .reduce(0, (accumulator, value) -> value - accumulator);
    }

    private int getLastValue() {
        return numbers.get(numbers.size() - 1);
    }

    private List<Sequence> computeSubSequences() {
        List<Sequence> subSequences = new ArrayList<>();
        Sequence differencesSubSequence = new Sequence(numbers);
        subSequences.add(differencesSubSequence);

        while (differencesSubSequence.numbers().stream().anyMatch(value -> 0 != value)) {
            Sequence finalDifferencesSubSequence = differencesSubSequence;
            List<Integer> differences = IntStream.range(1, differencesSubSequence.numbers().size())
                    .mapToObj(i -> finalDifferencesSubSequence.numbers().get(i) - finalDifferencesSubSequence.numbers().get(i - 1))
                    .toList();
            differencesSubSequence = new Sequence(differences);
            subSequences.add(differencesSubSequence);
        }
        return subSequences;
    }
}
