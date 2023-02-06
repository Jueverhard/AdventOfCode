package calendar.year._2022.day09;

import java.util.Set;

public abstract class Rope {

    protected Set<Position> tailPositions;

    abstract void moveHead(Direction direction, int nbMoves);

    int getNbVisitedPositions() {
        return tailPositions.size();
    }
}
