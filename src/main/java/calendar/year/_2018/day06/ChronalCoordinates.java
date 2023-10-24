package calendar.year._2018.day06;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChronalCoordinates extends Exercise {
    public ChronalCoordinates(LocalDate date) {
        super(date);
    }

    record Extremities(int xMax, int yMax) {
    }

    private Extremities extremities;

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<Position> positions = new ArrayList<>();
        int currentCharCode = 65; // `65` is the code for `A`
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            Pattern numberPattern = Pattern.compile("\\d+");
            String input;
            while (null != (input = br.readLine())) {
                List<Integer> coordinates = numberPattern.matcher(input).results()
                        .map(MatchResult::group)
                        .map(Integer::parseInt)
                        .toList();

                positions.add(new Position(coordinates.get(0), coordinates.get(1), (char) currentCharCode++));
            }
        }

        // Compute every position closest beacon
        Map<Position, Character> closestPointsArea = computeMinimalDistancesArea(positions);

        // Identify the beacon associated to infinite areas
        Set<Character> infiniteAreas = closestPointsArea.entrySet().stream()
                .filter(entry -> entry.getKey().isAtExtremity(extremities))
                .map(Entry::getValue)
                .collect(Collectors.toSet());

        // Compute the size of the largest area
        long largestAreaSize = closestPointsArea.entrySet().stream()
                .collect(Collectors.groupingBy(
                        Entry::getValue,
                        Collectors.counting()
                )).entrySet().stream()
                .filter(entry -> !infiniteAreas.contains(entry.getKey()))
                .map(Entry::getValue)
                .max(Long::compareTo)
                .orElseThrow();

        return print(largestAreaSize);
    }

    private Map<Position, Character> computeMinimalDistancesArea(List<Position> positions) {
        int maxX = positions.stream()
                .map(Position::x)
                .max(Integer::compareTo)
                .orElseThrow();
        int maxY = positions.stream()
                .map(Position::y)
                .max(Integer::compareTo)
                .orElseThrow();
        extremities = new Extremities(maxX, maxY);

        return IntStream.range(0, maxX + 2)
                .mapToObj(x -> IntStream.range(0, maxY + 1)
                        .mapToObj(y -> new Position(x, y, '.'))
                        .collect(Collectors.toSet())
                )
                .flatMap(Set::stream)
                .collect(Collectors.toMap(
                        Function.identity(),
                        position -> computeClosestPosition(position, positions)
                ));
    }

    private char computeClosestPosition(Position position, List<Position> positions) {
        record PositionDistance(Position pos, int distance) {}

        List<PositionDistance> ascendingPositionDistances = positions.stream()
                .map(targetPosition -> new PositionDistance(
                        targetPosition,
                        position.computeManhattanDistance(targetPosition)
                ))
                .sorted(Comparator.comparingInt(PositionDistance::distance))
                .toList();

        return ascendingPositionDistances.get(0).distance() == ascendingPositionDistances.get(1).distance() ?
                '.' : ascendingPositionDistances.get(0).pos().c();
    }
}
