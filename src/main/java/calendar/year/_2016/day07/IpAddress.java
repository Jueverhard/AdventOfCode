package calendar.year._2016.day07;

import java.util.Set;
import java.util.stream.IntStream;

public record IpAddress(Set<String> outsideBrackets, Set<String> insideBrackets) {

    private static final int ABBA_SIZE = 4;

    public boolean isValid() {
        return insideBrackets.stream().noneMatch(this::containsAbbaSequence) &&
                outsideBrackets.stream().anyMatch(this::containsAbbaSequence);
    }

    private boolean containsAbbaSequence(String chain) {
        return IntStream.rangeClosed(0, chain.length() - ABBA_SIZE)
                .mapToObj(i -> chain.substring(i, i + ABBA_SIZE))
                .filter(word -> word.charAt(0) != word.charAt(1))
                .anyMatch(word -> word.charAt(0) == word.charAt(3) && word.charAt(1) == word.charAt(2));
    }
}
