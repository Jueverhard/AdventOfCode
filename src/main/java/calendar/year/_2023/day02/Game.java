package calendar.year._2023.day02;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
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
        return cubeSets.stream()
                .map(CubeSet::cubes)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.groupingBy(
                        Entry::getKey,
                        Collectors.mapping(Entry::getValue, Collectors.toList())
                )).values().stream()
                .map(Collections::max)
                .reduce(1, (a, b) -> a * b);
    }
}
