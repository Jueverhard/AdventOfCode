package calendar.year._2024.day06;

public enum Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT;

    public Direction getNextDirection() {
        Direction[] directions = Direction.values();
        return directions[(this.ordinal() + 1) % directions.length];
    }
}
