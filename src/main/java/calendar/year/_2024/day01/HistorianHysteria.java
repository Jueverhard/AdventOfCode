package calendar.year._2024.day01;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HistorianHysteria extends Exercise {

    public HistorianHysteria(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<Integer> leftLocationIds = new ArrayList<>();
        List<Integer> rightLocationIds = new ArrayList<>();

        // Data initialization
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            Pattern numberPattern = Pattern.compile("\\d+");
            while (null != (line = br.readLine())) {
                List<Integer> inputParts = numberPattern.matcher(line).results()
                        .map(MatchResult::group)
                        .map(Integer::parseInt)
                        .toList();
                leftLocationIds.add(inputParts.get(0));
                rightLocationIds.add(inputParts.get(1));
            }
        }

        int result = Part.PART_1 == part ?
                sumsUpAscendingDeltas(leftLocationIds, rightLocationIds) :
                computeSimilarityScore(leftLocationIds, rightLocationIds);

        return print(result);
    }

    /**
     * Calculates the sum of the absolute differences between two integer lists.
     *
     * @param leftLocationIds An integer list.
     * @param rightLocationIds Another integer list.
     * @return The sum of the lists deltas.
     */
    private int sumsUpAscendingDeltas(final List<Integer> leftLocationIds, final List<Integer> rightLocationIds) {
        List<Integer> orderedLefts = leftLocationIds.stream()
                .sorted()
                .toList();
        List<Integer> orderedRights = rightLocationIds.stream()
                .sorted()
                .toList();

        return IntStream.range(0, orderedLefts.size())
                .map(i -> Math.abs(orderedLefts.get(i) - orderedRights.get(i)))
                .sum();
    }

    /**
     * Computes a similarity score between two lists of location IDs. The score consists of each location ID multiplied
     * by its number of occurrences.
     *
     * @param leftLocationIds An integer list representing the location IDs to be weighted.
     * @param rightLocationIds An integer list where the occurrences of each ID will be counted.
     * @return The resulting similarity score.
     */
    private int computeSimilarityScore(List<Integer> leftLocationIds, List<Integer> rightLocationIds) {
        Map<Integer, Long> locationIdOccurences = rightLocationIds.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        return leftLocationIds.stream()
                .map(locationId -> locationId * locationIdOccurences.getOrDefault(locationId, 0L))
                .reduce(Long::sum).orElseThrow()
                .intValue();
    }
}
