package calendar.year._2021.day08;

import lombok.Getter;

import java.util.Set;

@Getter
public enum Digit {
    ZERO(Set.of('a', 'b', 'c', 'e', 'f', 'g')),
    ONE(Set.of('c', 'f')),
    TWO(Set.of('a', 'c', 'd', 'e', 'g')),
    THREE(Set.of('a', 'c', 'd', 'f', 'g')),
    FOUR(Set.of('b', 'c', 'd', 'f')),
    FIVE(Set.of('a', 'b', 'd', 'f', 'g')),
    SIX(Set.of('a', 'b', 'd', 'e', 'f', 'g')),
    SEVEN(Set.of('a', 'c', 'f')),
    EIGHT(Set.of('a', 'b', 'c', 'd', 'e', 'f', 'g')),
    NINE(Set.of('a', 'b', 'c', 'd', 'f', 'g'));

    private final Set<Character> segments;

    Digit(Set<Character> segments) {
        this.segments = segments;
    }

    public static final Set<Digit> UNIQUE_SEGMENT_SIZED_DIGIT = Set.of(ONE, FOUR, SEVEN, EIGHT);
}
