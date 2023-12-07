package calendar.year._2023.day07;

import java.util.HashMap;
import java.util.Map;

public enum Kind {
    A("A"), K("K"), Q("Q"), J("J"), T("T"), NINE("9"), EIGHT("8"), SEVEN("7"), SIX("6"), FIVE("5"), FOUR("4"), THREE("3"), TWO("2");

    private static final Map<String, Kind> BY_LABEL = new HashMap<>();

    static {
        for (Kind k : values()) {
            BY_LABEL.put(k.label, k);
        }
    }

    private final String label;

    Kind(String label) {
        this.label = label;
    }

    public static Kind fromValue(String label) {
        return BY_LABEL.get(label);
    }
}
