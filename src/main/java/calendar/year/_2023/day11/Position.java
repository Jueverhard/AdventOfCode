package calendar.year._2023.day11;

public record Position(long x, long y) {

    public long computeManhattanDistance(Position position) {
        return Math.abs(x - position.x()) + Math.abs(y - position.y());
    }
}
