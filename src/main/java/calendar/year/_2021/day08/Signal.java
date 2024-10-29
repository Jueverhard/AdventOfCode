package calendar.year._2021.day08;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public record Signal(Set<Character> segments) {

    public int length() {
        return segments.size();
    }

    /**
     * Transforms the current signal using the provided mapping rules.
     *
     * @param mappingRules A map where the keys are the scrambled characters of the segments
     *                     and the values are the original ones to be restored.
     * @return A new Signal instance with segments mapped based on the provided mapping rules.
     */
    public Signal transform(Map<Character, Character> mappingRules) {
        Set<Character> mappedCharacters = segments.stream()
                .map(mappingRules::get)
                .collect(Collectors.toSet());

        return new Signal(mappedCharacters);
    }
}
