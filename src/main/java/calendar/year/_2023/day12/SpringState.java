package calendar.year._2023.day12;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum SpringState {
    OPERATIONAL('.'),
    DAMAGED('#'),
    UNKNOWN('?');

    private static final Map<Character, SpringState> BY_LABEL = new HashMap<>();

    static {
        Arrays.stream(values()).
                forEach(springState -> BY_LABEL.put(springState.label, springState));
    }

    @Getter
    private final char label;

    SpringState(char label) {
        this.label = label;
    }

    public static SpringState fromValue(char label) {
        return BY_LABEL.get(label);
    }
}
