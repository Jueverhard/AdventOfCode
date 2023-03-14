package calendar.year._2021.day02;

public enum Direction {
    FORWARD, DOWN, UP;

    static Direction fromValue(String direction) {
        return Direction.valueOf(direction.toUpperCase());
    }
}
