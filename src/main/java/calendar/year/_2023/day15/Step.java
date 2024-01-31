package calendar.year._2023.day15;

import java.util.concurrent.atomic.AtomicInteger;

public record Step(String text) {

    public int computeHashValue() {
        final AtomicInteger hashValue = new AtomicInteger(0);
        text.chars()
                .forEach(asciiChar -> hashValue.set((hashValue.get() + asciiChar) * 17 % 256));

        return hashValue.get();
    }
}
