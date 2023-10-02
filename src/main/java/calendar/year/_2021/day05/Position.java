package calendar.year._2021.day05;

import utils.enums.Part;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public record Position(int x, int y) {

    public List<Position> computeIntermediatePositions(Position position, Part part) {
        Set<Position> intermediatePositions = new HashSet<>();

        if (this.x == position.x) {
            int start = Math.min(this.y, position.y);
            int end = Math.max(this.y, position.y);

            intermediatePositions.addAll(IntStream.range(start, end)
                    .mapToObj(yPos -> new Position(this.x, yPos))
                    .toList());
        } else if (this.y == position.y) {
            int start = Math.min(this.x, position.x);
            int end = Math.max(this.x, position.x);

            intermediatePositions.addAll(IntStream.range(start, end)
                    .mapToObj(xPos -> new Position(xPos, this.y))
                    .toList());
        } else if (Part.PART_2 == part) {
            intermediatePositions.addAll(computeDiagonals(position));
        }

        if (!intermediatePositions.isEmpty()) {
            intermediatePositions.add(new Position(this.x, this.y));
            intermediatePositions.add(new Position(position.x(), position.y()));
        }

        return new ArrayList<>(intermediatePositions);
    }

    private Set<Position> computeDiagonals(Position position) {
        Set<Position> diagonals = new HashSet<>();
        int xIncrement = this.x > position.x() ? -1 : 1;
        int yIncrement = this.y > position.y() ? -1 : 1;
        int currentX = this.x;
        int currentY = this.y;

        while (currentX != position.x()) {
            diagonals.add(new Position(currentX, currentY));
            currentX += xIncrement;
            currentY += yIncrement;
        }

        return diagonals;
    }
}
