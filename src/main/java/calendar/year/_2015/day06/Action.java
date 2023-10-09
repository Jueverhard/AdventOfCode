package calendar.year._2015.day06;

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

public enum Action {
    TURN_ON("turn on", isOn -> true),
    TURN_OFF("turn off", isOn -> false),
    TOGGLE("toggle", isOn -> !isOn);

    private static final Map<String, Action> BY_TEXT = new HashMap<>();

    static {
        for (Action action: values()) {
            BY_TEXT.put(action.text, action);
        }
    }

    private final String text;

    private final UnaryOperator<Boolean> updateFunction;

    Action(String text, UnaryOperator<Boolean> updateFunction) {
        this.text = text;
        this.updateFunction = updateFunction;
    }

    public static Action fromText(String text) {
        return BY_TEXT.get(text);
    }

    public boolean apply(boolean isOn) {
        return updateFunction.apply(isOn);
    }
}
