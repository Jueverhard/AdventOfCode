package calendar.year._2015.day05;

import lombok.AllArgsConstructor;

import java.util.Set;
import java.util.stream.IntStream;

@AllArgsConstructor
public class NiceStringCandidate {

    private static final Set<Character> VOWELS = Set.of('a', 'e', 'i', 'o', 'u');
    private static final Set<String> BANISHED_STRINGS = Set.of("ab", "cd", "pq", "xy");

    protected String candidate;

    public boolean isNice() {
        if (containsAnyBanishedString()) {
            return false;
        }

        return containsAtLeastThreeVowels() && containsAtLeastADuplicateLetter();
    }

    private boolean containsAtLeastThreeVowels() {
        return this.candidate.chars()
                .mapToObj(c -> (char) c)
                .filter(VOWELS::contains)
                .count() >= 3;
    }

    private boolean containsAtLeastADuplicateLetter() {
        return IntStream.range(0, this.candidate.length() - 1)
                .mapToObj(index -> this.candidate.substring(index, index + 2))
                .anyMatch(string -> string.charAt(0) == string.charAt(1));
    }

    private boolean containsAnyBanishedString() {
        return BANISHED_STRINGS.stream()
                .anyMatch(banished -> this.candidate.contains(banished));
    }
}
