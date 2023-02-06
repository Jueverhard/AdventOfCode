package calendar.year._2022.day09;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class Position {

    private int x;

    private int y;

    public void move(Direction direction) {
        switch (direction) {
            case L:
                x--;
                break;
            case R:
                x++;
                break;
            case U:
                y--;
                break;
            case D:
                y++;
        }
    }

    public void follow(Position position) {
        int xDelta = Math.abs(this.x - position.x);
        int yDelta = Math.abs(this.y - position.y);

        // If the position is too far away, follows it
        if (xDelta > 1 || yDelta > 1) {
            directionsToFollow(position, xDelta, yDelta).forEach(this::move);
        }
    }

    private Set<Direction> directionsToFollow(Position position, int xDelta, int yDelta) {
        Set<Direction> directions = new HashSet<>();
        if (xDelta > yDelta) {
            directions.add(horizontalMove(position));
            if (xDelta + yDelta > 2) {
                directions.add(verticalMove(position));
            }
        } else {
            directions.add(verticalMove(position));
            if (xDelta + yDelta > 2) {
                directions.add(horizontalMove(position));
            }
        }

        return directions;
    }

    private Direction verticalMove(Position position) {
        return position.y > this.y ? Direction.D : Direction.U;
    }

    private Direction horizontalMove(Position position) {
        return position.x > this.x ? Direction.R : Direction.L;
    }

    public Position copy() {
        return new Position(x, y);
    }
}
