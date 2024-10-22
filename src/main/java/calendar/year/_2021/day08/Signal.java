package calendar.year._2021.day08;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public record Signal(Set<Character> segments) {

    public int length() {
        return segments.size();
    }

    public Signal transform(Map<Character, Character> mappingRules) {
        Set<Character> mappedCharacters = segments.stream()
                .map(mappingRules::get)
                .collect(Collectors.toSet());

        return new Signal(mappedCharacters);
    }
}
