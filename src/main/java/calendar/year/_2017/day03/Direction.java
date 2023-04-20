package calendar.year._2017.day03;

import java.util.List;

public enum Direction {
    RIGHT, UP, LEFT, DOWN;

    public Direction getNextDirection() {
        Direction[] directions = Direction.values();
        int currentDirectionIndex = List.of(directions).indexOf(this);
        return directions[(currentDirectionIndex + 1) % directions.length];
    }
}
