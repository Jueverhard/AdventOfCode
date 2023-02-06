package calendar.year._2016.day01;

import java.util.List;

public enum Direction {
    N, E, S, W;

    public Direction turn(Turn turn) {
        List<Direction> directions = List.of(Direction.values());
        int index = directions.indexOf(this);
        int newIndex;
        switch (turn) {
            case L:
                newIndex = (index - 1 + directions.size()) % directions.size();
                return directions.get(newIndex);
            case R:
                newIndex = (index + 1) % directions.size();
                return directions.get(newIndex);
            default:
                throw new IllegalArgumentException();
        }
    }
}
