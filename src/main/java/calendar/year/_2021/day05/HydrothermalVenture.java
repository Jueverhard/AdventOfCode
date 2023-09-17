package calendar.year._2021.day05;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HydrothermalVenture extends Exercise {
    public HydrothermalVenture(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<Segment> segments = new ArrayList<>();
        Pattern digitsPattern = Pattern.compile("\\d+");
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            while (null != (line = br.readLine())) {
                List<Integer> digits = digitsPattern.matcher(line).results()
                        .map(MatchResult::group)
                        .map(Integer::parseInt)
                        .toList();
                Position start = new Position(digits.get(0), digits.get(1), part);
                Position end = new Position(digits.get(2), digits.get(3), part);

                segments.add(new Segment(start, end));
            }
        }

        long nbOverlappingPositions = segments.stream()
                .map(Segment::getAllPositions)
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                )).entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .count();

        return print(nbOverlappingPositions);
    }
}
