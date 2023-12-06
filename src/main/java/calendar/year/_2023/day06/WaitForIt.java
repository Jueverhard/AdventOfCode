package calendar.year._2023.day06;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class WaitForIt extends Exercise {

    public WaitForIt(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<Integer> times;
        List<Integer> bestDistances;
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            Pattern digitPattern = Pattern.compile("\\d+");
            times = digitPattern.matcher(br.readLine()).results()
                    .map(MatchResult::group)
                    .map(Integer::parseInt)
                    .toList();
            bestDistances = digitPattern.matcher(br.readLine()).results()
                    .map(MatchResult::group)
                    .map(Integer::parseInt)
                    .toList();
        }

        long result;
        if (Part.PART_1 == part) {
            result = IntStream.range(0, times.size())
                    .mapToObj(i -> new RaceRecord(times.get(i), bestDistances.get(i)))
                    .map(RaceRecord::computeAllRecordBreakingPossibilities)
                    .reduce(1L, (a, b) -> a * b);
        } else {
            result = new RaceRecord(concatNumbers(times), concatNumbers(bestDistances))
                    .computeAllRecordBreakingPossibilities();
        }

        return print(result);
    }

    /**
     * @param integers A list of numbers
     * @return A single numbers, corresponding to all the given numbers concatenated
     */
    private long concatNumbers(List<Integer> integers) {
        return integers.stream()
                .map(String::valueOf)
                .reduce(String::concat)
                .map(Long::parseLong)
                .orElseThrow();
    }
}
