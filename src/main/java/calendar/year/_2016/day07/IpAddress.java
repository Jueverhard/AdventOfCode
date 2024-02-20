package calendar.year._2016.day07;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record IpAddress(Set<String> outsideBrackets, Set<String> insideBrackets) {

    private static final int TLS_SEQUENCE_SIZE = 4;
    private static final int SSL_SEQUENCE_SIZE = 3;

    public boolean supportsTLS() {
        return insideBrackets.stream().noneMatch(this::containsAbbaSequence) &&
                outsideBrackets.stream().anyMatch(this::containsAbbaSequence);
    }

    private boolean containsAbbaSequence(String chain) {
        return IntStream.rangeClosed(0, chain.length() - TLS_SEQUENCE_SIZE)
                .mapToObj(i -> chain.substring(i, i + TLS_SEQUENCE_SIZE))
                .filter(word -> word.charAt(0) != word.charAt(1))
                .anyMatch(word -> word.charAt(0) == word.charAt(3) && word.charAt(1) == word.charAt(2));
    }

    public boolean supportsSSL() {
        return outsideBrackets.stream()
                .map(this::extractAbaSequences)
                .flatMap(Set::stream)
                .anyMatch(abaSequence -> insideBrackets.stream()
                        .map(this::extractAbaSequences)
                        .flatMap(Set::stream)
                        .anyMatch(babSequence -> abaSequence.charAt(0) == babSequence.charAt(1) && abaSequence.charAt(1) == babSequence.charAt(0))
                );
    }

    private Set<String> extractAbaSequences(String chain) {
        return IntStream.rangeClosed(0, chain.length() - SSL_SEQUENCE_SIZE)
                .mapToObj(i -> chain.substring(i, i + SSL_SEQUENCE_SIZE))
                .filter(word -> word.charAt(0) != word.charAt(1) && word.charAt(0) == word.charAt(2))
                .collect(Collectors.toSet());
    }
}
