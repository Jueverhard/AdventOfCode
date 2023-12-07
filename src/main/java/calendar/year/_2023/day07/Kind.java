package calendar.year._2023.day07;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum Kind {
    A("A"), K("K"), Q("Q"), J("J"), T("T"), NINE("9"), EIGHT("8"), SEVEN("7"), SIX("6"), FIVE("5"), FOUR("4"), THREE("3"), TWO("2");

    private static final Map<String, Kind> BY_LABEL = new HashMap<>();
    private static final EnumMap<Kind, Integer> INDEX_WITH_JOKERS = new EnumMap<>(Kind.class);

    static {
        List<Kind> test = Arrays.stream(values()).collect(Collectors.toList());
        test.remove(Kind.J);
        test.add(Kind.J);
        for (int i = 0; i < test.size(); i++) {
            Kind k = test.get(i);
            BY_LABEL.put(k.label, k);
            INDEX_WITH_JOKERS.put(k, i);
        }
    }

    private final String label;

    Kind(String label) {
        this.label = label;
    }

    public static Kind fromValue(String label) {
        return BY_LABEL.get(label);
    }

    /**
     * Compare two card kinds, knowing that jokers are at the bottom
     *
     * @param o The card kind to be compared to
     * @return 1 if the current card kind is stronger, -1 if weaker, 0 if equals
     */
    public int compareUsingJokers(Kind o) {
        return INDEX_WITH_JOKERS.get(this) - INDEX_WITH_JOKERS.get(o);
    }
}
