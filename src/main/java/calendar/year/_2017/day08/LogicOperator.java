package calendar.year._2017.day08;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiPredicate;

@RequiredArgsConstructor
public enum LogicOperator {
    GREATER(">", (a, b) -> a > b),
    GREATER_OR_EQUAL(">=", (a, b) -> a >= b),
    LOWER("<", (a, b) -> a < b),
    LOWER_OR_EQUAL("<=", (a, b) -> a <= b),
    EQUAL("==", Objects::equals),
    DIFFERENT("!=", (a, b) -> !Objects.equals(a, b));

    private static final Map<String, LogicOperator> BY_SIGN = new HashMap<>();

    static {
        Arrays.stream(values())
                .forEach(pipeKind -> BY_SIGN.put(pipeKind.sign, pipeKind));
    }

    private final String sign;

    private final BiPredicate<Integer, Integer> comparator;

    public static LogicOperator fromValue(String sign) {
        return BY_SIGN.get(sign);
    }

    public boolean compute(int a, int b) {
        return comparator.test(a, b);
    }
}
