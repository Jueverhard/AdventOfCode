package calendar.year._2015.day06;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Position(int x, int y) {

    /**
     * Compute all intermediate positions (including extremities)
     *
     * @param position The second area extremity
     * @return Every intermediate positions
     */
    public Set<Position> computeIntermediatePositions(Position position) {
        int xMin = Math.min(this.x(), position.x());
        int xMax = Math.max(this.x(), position.x());
        int yMin = Math.min(this.y(), position.y());
        int yMax = Math.max(this.y(), position.y());

        return IntStream.range(xMin, xMax + 1)
                .mapToObj(xPos -> IntStream.range(yMin, yMax + 1)
                        .mapToObj(yPos -> new Position(xPos, yPos))
                        .collect(Collectors.toSet())
                )
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }
}
