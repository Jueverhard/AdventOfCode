package calendar.year._2023.day02;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Game {

    @Getter
    private final int id;

    private final Set<CubeSet> cubeSets;

    private static final Map<Color, Integer> LIMITS = Map.of(
            Color.RED, 12,
            Color.GREEN, 13,
            Color.BLUE, 14
    );

    public Game(String input) {
        String[] gameData = input.split(": ");
        this.id = Integer.parseInt(gameData[0].split(" ")[1]);
        String[] cubeSetsData = gameData[1].split("; ");
        this.cubeSets = Arrays.stream(cubeSetsData)
                .map(this::extractFrom)
                .collect(Collectors.toSet());
    }

    private CubeSet extractFrom(String input) {
        String[] cubesData = input.split(", ");
        Map<Color, Integer> cubes = Arrays.stream(cubesData)
                .collect(Collectors.toMap(
                        cubeData -> Color.fromValue(cubeData.split(" ")[1]),
                        cubeData -> Integer.parseInt(cubeData.split(" ")[0])
                ));
        return new CubeSet(cubes);
    }

    /**
     * @return True whether the game is within the cubes limits
     */
    public boolean isLegit() {
        return cubeSets.stream()
                .allMatch(cubeSet -> cubeSet.isLegit(LIMITS));
    }

    /**
     * @return The game power, corresponding to the product of the included cubes
     */
    public int computePower() {
        Map<Color, Integer> test = cubeSets.stream()
                .map(cubeSet -> cubeSet.cubes().entrySet()
                        .stream()
                        .toList()
                )
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(Entry::getKey)).entrySet().stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        entry -> entry.getValue().stream()
                                .map(Entry::getValue)
                                .max(Integer::compareTo)
                                .orElseThrow()
                ));
        return test.values().stream().reduce(1, (a, b) -> a * b);
    }
}
