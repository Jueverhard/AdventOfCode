package calendar.year._2023.day18;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LavaductLagoon extends Exercise {

    public LavaductLagoon(LocalDate date) {
        super(date);
    }

    record Extremities(int xMin, int yMin, int xMax, int yMax) {}

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        // Initialize data from the input
        List<DigInstruction> digInstructions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            Pattern pattern = Pattern.compile("(?<direction>\\w) (?<volume>\\d+) \\(#(?<hexa>\\w+)");
            String line;
            while (null != (line = br.readLine())) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    Direction direction = Direction.valueOf(matcher.group("direction"));
                    int volume = Integer.parseInt(matcher.group("volume"));
                    String hexa = matcher.group("hexa");
                    digInstructions.add(new DigInstruction(direction, volume, hexa));
                }
            }
        }

        // Use the instructions to dug up the field
        Set<Position> dugUpPositions = new HashSet<>();
        Position currentPosition = new Position(0, 0);
        for (DigInstruction digInstruction : digInstructions) {
            List<Position> newlyDugUpPositions = currentPosition.computeIntermediatePositions(
                    digInstruction.direction(),
                    digInstruction.volume()
            );
            dugUpPositions.addAll(newlyDugUpPositions);
            currentPosition = newlyDugUpPositions.get(newlyDugUpPositions.size() - 1);
        }

        // Dug up the lagoon defined by the instructions
        Extremities extremities = new Extremities(
                dugUpPositions.stream()
                        .min(Comparator.comparingInt(Position::x))
                        .orElseThrow().x(),
                dugUpPositions.stream()
                        .min(Comparator.comparingInt(Position::y))
                        .orElseThrow().y(),
                dugUpPositions.stream()
                        .max(Comparator.comparingInt(Position::x))
                        .orElseThrow().x(),
                dugUpPositions.stream()
                        .max(Comparator.comparingInt(Position::y))
                        .orElseThrow().y()
        );

        Set<Position> newPositionsToDugUp = IntStream.range(extremities.xMin(), extremities.xMax())
                .mapToObj(x -> IntStream.range(extremities.yMin(), extremities.yMax())
                        .mapToObj(y -> new Position(x, y))
                        .filter(newPosition -> newPosition.isBeetweenBoundaries(dugUpPositions))
                        .collect(Collectors.toSet())
                )
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
        dugUpPositions.addAll(newPositionsToDugUp);

        int result = dugUpPositions.size();
        return print(result);
    }
}
