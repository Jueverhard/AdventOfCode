package calendar.year._2018.day06;

import calendar.year._2018.day06.ChronalCoordinates.Extremities;

public record Position(int x, int y) {

    public int computeManhattanDistance(Position p) {
        return Math.abs(this.x - p.x()) + Math.abs(this.y - p.y());
    }

    public boolean isAtExtremity(Extremities extremities) {
        return 0 == this.x || extremities.xMax() == this.x ||
                0 == this.y || extremities.yMax() == this.y;
    }
}
