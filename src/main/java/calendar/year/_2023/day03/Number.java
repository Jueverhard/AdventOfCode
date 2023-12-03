package calendar.year._2023.day03;

import java.util.Set;

public record Number(Position start, Position end, int value) {

    /**
     * @param symbols The symbols
     * @return True whether the number part is near any symbol
     */
    public boolean isNearAnySymbol(Set<Symbol> symbols) {
        return start.computeIntermediatePositions(end).stream()
                .anyMatch(position -> symbols.stream()
                        .anyMatch(symbol -> symbol.position().isNear(position))
                );
    }
}
