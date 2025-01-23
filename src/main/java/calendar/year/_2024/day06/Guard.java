package calendar.year._2024.day06;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class Guard {

    private Position position;

    private Direction orientation;

    public void move(Set<Position> obstacles) {
        Position nextPosition = position.copyOf();
        Direction nextOrientation = orientation;
        nextPosition.move(nextOrientation);
        while (obstacles.contains(nextPosition)) {
            nextOrientation = nextOrientation.getNextDirection();
            nextPosition = position.copyOf();
            nextPosition.move(nextOrientation);
        }
        position = nextPosition;
        orientation = nextOrientation;
    }

    public Guard copyOf() {
        return new Guard(position.copyOf(), orientation);
    }
}
