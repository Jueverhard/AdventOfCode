package calendar.year._2023.day10;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum PipeKind {
    VERTICAL('|'),
    HORIZONTAL('-'),
    NORTH_EAST('L'),
    NORTH_WEST('J'),
    SOUTH_WEST('7'),
    SOUTH_EAST('F'),
    GROUND('.'),
    STARTING_POINT('S');

    private static final Map<Character, PipeKind> BY_LABEL = new HashMap<>();

    static {
        Arrays.stream(values())
                .forEach(pipeKind -> BY_LABEL.put(pipeKind.label, pipeKind));
    }

    private final char label;

    PipeKind(char label) {
        this.label = label;
    }

    public static PipeKind fromValue(char label) {
        return BY_LABEL.get(label);
    }

    public boolean canGo(Direction direction) {
        return switch (direction) {
            case UP -> Set.of(STARTING_POINT, VERTICAL, NORTH_EAST, NORTH_WEST).contains(this);
            case DOWN -> Set.of(STARTING_POINT, VERTICAL, SOUTH_EAST, SOUTH_WEST).contains(this);
            case LEFT -> Set.of(STARTING_POINT, HORIZONTAL, NORTH_WEST, SOUTH_WEST).contains(this);
            case RIGHT -> Set.of(STARTING_POINT, HORIZONTAL, NORTH_EAST, SOUTH_EAST).contains(this);
        };
    }
}
