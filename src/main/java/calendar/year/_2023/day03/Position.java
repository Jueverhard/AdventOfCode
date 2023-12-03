package calendar.year._2023.day03;

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

    /**
     * @param position The second position to compare to
     * @return True whether the two positions are near (i.e. no further apart than one block, diagonals included)
     */
    public boolean isNear(Position position) {
        return Math.abs(this.x - position.x) == 1 && Math.abs(this.y - position.y) <= 1
                || Math.abs(this.y - position.y) == 1 && Math.abs(this.x - position.x) <= 1;
    }
}
