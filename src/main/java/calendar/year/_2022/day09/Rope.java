package calendar.year._2022.day09;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Rope {

    private final Set<Position> tailPositions;

    private final List<Position> knots;

    public Rope(int nbKnots) {
        knots = IntStream.range(0, nbKnots)
                .mapToObj(elt -> new Position(0, 0))
                .toList();
        tailPositions = new HashSet<>();
    }

    public void moveHead(Direction direction, int nbMoves) {
        while (nbMoves > 0) {
            moveHead(direction);
            for (int i = 1; i < knots.size(); i++) {
                Position relativeTail = knots.get(i);
                Position relativeHead = knots.get(i - 1);
                relativeTail.follow(relativeHead);
            }
            Position tail = knots.get(knots.size() - 1);
            tailPositions.add(tail.copy());
            nbMoves--;
        }
    }

    private void moveHead(Direction direction) {
        knots.get(0).move(direction);
    }

    int getNbVisitedPositions() {
        return tailPositions.size();
    }
}
