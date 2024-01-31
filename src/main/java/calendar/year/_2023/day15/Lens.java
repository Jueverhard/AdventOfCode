package calendar.year._2023.day15;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class Lens {

    private final String label;

    private final char operand;

    private final int power;

    private static final Pattern PATTERN = Pattern.compile("(?<label>\\w+)(?<operand>[-=])(?<power>\\d+)?");

    public Lens(String text) {
        Matcher matcher = PATTERN.matcher(text);
        if (!matcher.find()) {
            throw new IllegalArgumentException();
        }

        this.label = matcher.group("label");
        this.operand = matcher.group("operand").charAt(0);
        String lensPower = matcher.group("power");
        this.power = null == lensPower ? 0 : Integer.parseInt(lensPower);
    }

    public int computeHashValue() {
        final AtomicInteger hashValue = new AtomicInteger(0);
        label.chars()
                .forEach(asciiChar -> hashValue.set((hashValue.get() + asciiChar) * 17 % 256));

        return hashValue.get();
    }
}
