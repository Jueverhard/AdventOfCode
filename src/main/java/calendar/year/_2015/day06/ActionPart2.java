package calendar.year._2015.day06;

import java.util.HashMap;
import java.util.Map;

public enum ActionPart2 {
    TURN_ON("turn on", 1),
    TURN_OFF("turn off", -1),
    TOGGLE("toggle", 2);

    private static final Map<String, ActionPart2> BY_TEXT = new HashMap<>();

    static {
        for (ActionPart2 action: values()) {
            BY_TEXT.put(action.text, action);
        }
    }

    private final String text;

    private final int increment;

    ActionPart2(String text, int increment) {
        this.text = text;
        this.increment = increment;
    }

    public static ActionPart2 fromText(String text) {
        return BY_TEXT.get(text);
    }

    public int apply(int brightness) {
        return Math.max(0, brightness + increment);
    }
}
