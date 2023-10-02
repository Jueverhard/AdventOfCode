package calendar.year._2015.day06;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProbablyAFireHazard extends Exercise {
    public ProbablyAFireHazard(LocalDate date) {
        super(date);
    }

    private static final Pattern PATTERN = Pattern.compile("(?<action>[\\w ]+) (?<startX>\\d+),(?<startY>\\d+) .* (?<endX>\\d+),(?<endY>\\d+)");

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        long result = Part.PART_1 == part ? exercise1(testMode) : exercise2(testMode);
        return print(result);
    }

    private long exercise2(boolean testMode) throws IOException {
        // Initialize the lights grid with all lights off
        Map<Position, Integer> lightsGridBrightness = IntStream.range(0, 1000)
                .mapToObj(x -> IntStream.range(0, 1000)
                        .mapToObj(y -> new Position(x, y))
                        .collect(Collectors.toSet())
                )
                .flatMap(Set::stream)
                .collect(Collectors.toMap(Function.identity(), pos -> 0));

        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            while (null != (line = br.readLine())) {
                Matcher matcher = PATTERN.matcher(line);
                if (!matcher.find()) {
                    throw new IllegalArgumentException("Unexpected input format: " + line);
                }

                ActionPart2 action = ActionPart2.fromText(matcher.group("action"));
                Position start = new Position(Integer.parseInt(matcher.group("startX")), Integer.parseInt(matcher.group("startY")));
                Position end = new Position(Integer.parseInt(matcher.group("endX")), Integer.parseInt(matcher.group("endY")));

                applyAction(lightsGridBrightness, action, start, end);
            }
        }

        return lightsGridBrightness.values().stream()
                .reduce(0, Integer::sum);
    }

    private long exercise1(boolean testMode) throws IOException {
        // Initialize the lights grid with all lights off
        Map<Position, Boolean> lightsGrid = IntStream.range(0, 1000)
                .mapToObj(x -> IntStream.range(0, 1000)
                        .mapToObj(y -> new Position(x, y))
                        .collect(Collectors.toSet())
                )
                .flatMap(Set::stream)
                .collect(Collectors.toMap(Function.identity(), pos -> false));

        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            while (null != (line = br.readLine())) {
                Matcher matcher = PATTERN.matcher(line);
                if (!matcher.find()) {
                    throw new IllegalArgumentException("Unexpected input format: " + line);
                }

                Action action = Action.fromText(matcher.group("action"));
                Position start = new Position(Integer.parseInt(matcher.group("startX")), Integer.parseInt(matcher.group("startY")));
                Position end = new Position(Integer.parseInt(matcher.group("endX")), Integer.parseInt(matcher.group("endY")));

                applyAction(lightsGrid, action, start, end);
            }
        }

        return lightsGrid.values().stream()
                .filter(isOn -> isOn)
                .count();
    }

    /**
     * Update the lights grid following the action type and its operative zone
     *
     * @param lightsGrid The lights grid to update
     * @param action     The action to operate
     * @param start      The starting position
     * @param end        The ending position
     */
    private void applyAction(Map<Position, Boolean> lightsGrid, Action action, Position start, Position end) {
        // Computes every targeted positions
        Set<Position> positionsToUpdate = start.computeIntermediatePositions(end);

        // Apply the action to the targeted positions
        lightsGrid.entrySet().stream()
                .filter(entry -> positionsToUpdate.contains(entry.getKey()))
                .forEach(entry -> entry.setValue(action.apply(entry.getValue())));
    }

    /**
     * Update the lights grid following the action type and its operative zone
     *
     * @param lightsGrid The lights grid to update
     * @param action     The action to operate
     * @param start      The starting position
     * @param end        The ending position
     */
    private void applyAction(Map<Position, Integer> lightsGrid, ActionPart2 action, Position start, Position end) {
        // Computes every targeted positions
        Set<Position> positionsToUpdate = start.computeIntermediatePositions(end);

        // Apply the action to the targeted positions
        lightsGrid.entrySet().stream()
                .filter(entry -> positionsToUpdate.contains(entry.getKey()))
                .forEach(entry -> entry.setValue(action.apply(entry.getValue())));
    }
}
