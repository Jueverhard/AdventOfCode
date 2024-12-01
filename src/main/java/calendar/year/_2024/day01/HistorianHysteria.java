package calendar.year._2024.day01;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class HistorianHysteria extends Exercise {

    public HistorianHysteria(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<Integer> leftLocationIds = new ArrayList<>();
        List<Integer> rightLocationIds = new ArrayList<>();
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

        leftLocationIds.sort(Integer::compareTo);
        rightLocationIds.sort(Integer::compareTo);

        int result = IntStream.range(0, leftLocationIds.size())
                .map(i -> Math.abs(leftLocationIds.get(i) - rightLocationIds.get(i)))
                .sum();
        return print(result);
    }
}
