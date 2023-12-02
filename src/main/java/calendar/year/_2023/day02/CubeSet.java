package calendar.year._2023.day02;

import java.util.Map;

public record CubeSet(Map<Color, Integer> cubes) {

    public boolean isLegit(Map<Color, Integer> limits) {
        return cubes.entrySet().stream()
                .allMatch(cubeEntry -> cubeEntry.getValue() <= limits.get(cubeEntry.getKey()));
    }
}
