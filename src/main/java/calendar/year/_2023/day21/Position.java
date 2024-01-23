package calendar.year._2023.day21;

import java.util.HashSet;
import java.util.Set;

public record Position(int x, int y) {

    public Set<Position> computeReachablePositions(Set<Position> rocks) {
        Set<Position> reachablePositions = computeNearestPositions();
        reachablePositions.removeAll(rocks);
        return reachablePositions;
    }

    private Set<Position> computeNearestPositions() {
        return new HashSet<>(Set.of(
                new Position(x - 1, y),
                new Position(x + 1, y),
                new Position(x, y - 1),
                new Position(x, y + 1)
        ));
    }
}
