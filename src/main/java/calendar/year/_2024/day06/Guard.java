package calendar.year._2024.day06;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@AllArgsConstructor
@ToString
public class Guard {

    @Getter
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
}
