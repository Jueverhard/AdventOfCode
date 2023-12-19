package calendar.year._2023.day18;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public record Position(int x, int y) {

    public List<Position> computeIntermediatePositions(Direction direction, int range) {
        return switch (direction) {
            case U -> IntStream.rangeClosed(y, y + range)
                    .mapToObj(yPos -> new Position(x, yPos))
                    .toList();
            case R -> IntStream.rangeClosed(x, x + range)
                    .mapToObj(xPos -> new Position(xPos, y))
                    .toList();
            case D -> IntStream.rangeClosed(y - range, y)
                    .mapToObj(yPos -> new Position(x, yPos))
                    .sorted(Comparator.comparingInt(Position::y).reversed())
                    .toList();
            case L -> IntStream.rangeClosed(x - range, x)
                    .mapToObj(xPos -> new Position(xPos, y))
                    .sorted(Comparator.comparingInt(Position::x).reversed())
                    .toList();
        };
    }

    public boolean isBeetweenBoundaries(Set<Position> boundaries) {
        List<Position> northPositions = boundaries.stream()
                .filter(boundary -> boundary.x == x && boundary.y > y)
                .sorted(Comparator.comparingInt(Position::y))
                .toList();
        if (northPositions.isEmpty()) {
            return false;
        }

        int nbNorthGroups = 0;
        final List<Position> currentGroup = new ArrayList<>();
        if (1 == northPositions.size()) {
            nbNorthGroups++;
        } else {
            currentGroup.add(northPositions.get(0));
            for (int i = 1; i < northPositions.size(); i++) {
                Position northPos = northPositions.get(i);
                Position previousPosition = northPositions.get(i - 1);
                if (extractNorthGroup(northPos, previousPosition, currentGroup, boundaries, false)) {
                    nbNorthGroups++;
                    currentGroup.clear();
                }
                currentGroup.add(northPos);
            }
        }
        if (extractNorthGroup(new Position(0, 0), new Position(0, 0), currentGroup, boundaries, true)) {
            nbNorthGroups++;
            currentGroup.clear();
        }

        List<Position> westPositions = boundaries.stream()
                .filter(boundary -> boundary.y == y && boundary.x < x)
                .sorted(Comparator.comparingInt(Position::x))
                .toList();
        if (westPositions.isEmpty()) {
            return false;
        }

        int nbWestGroups = 0;
        List<Position> westCurrentGroup = new ArrayList<>();
        if (1 == westPositions.size()) {
            nbWestGroups++;
        } else {
            westCurrentGroup.add(westPositions.get(0));
            for (int i = 1; i < westPositions.size(); i++) {
                Position westPos = westPositions.get(i);
                Position previousPosition = westPositions.get(i - 1);
                if (extractWestGroup(westPos, previousPosition, westCurrentGroup, boundaries, false)) {
                    nbWestGroups++;
                    westCurrentGroup.clear();
                }
                westCurrentGroup.add(westPos);
            }
        }
        if (extractWestGroup(new Position(0, 0), new Position(0, 0), westCurrentGroup, boundaries, true)) {
            nbWestGroups++;
            westCurrentGroup.clear();
        }

        return 1 == nbNorthGroups % 2 && 1 == nbWestGroups % 2;
    }

    private boolean extractWestGroup(
            Position westPos,
            Position previousPosition,
            List<Position> currentGroup,
            Set<Position> boundaries,
            boolean isLast
    ) {
        if (Math.abs(westPos.x() - previousPosition.x()) != 1 || isLast) {
            boolean isToBeConsidered = boundaries.stream()
                    .filter(boundary -> currentGroup.stream().anyMatch(pos -> pos.x() == boundary.x()))
                    .filter(boundary -> boundary.y() - 1 == y || boundary.y() + 1 == y)
                    .map(Position::y)
                    .distinct()
                    .count() > 1;
            if (isToBeConsidered) {
                return true;
            }
            currentGroup.clear();
        }
        return false;
    }

    private boolean extractNorthGroup(
            Position northPos,
            Position previousPosition,
            final List<Position> currentGroup,
            Set<Position> boundaries,
            boolean isLast
    ) {
        if (Math.abs(northPos.y() - previousPosition.y()) != 1 || isLast) {
            boolean isToBeConsidered = boundaries.stream()
                    .filter(boundary -> currentGroup.stream().anyMatch(pos -> pos.y() == boundary.y()))
                    .filter(boundary -> boundary.x() - 1 == x || boundary.x() + 1 == x)
                    .map(Position::x)
                    .distinct()
                    .count() > 1;
            if (isToBeConsidered) {
                return true;
            }
            currentGroup.clear();
        }
        return false;
    }
}
