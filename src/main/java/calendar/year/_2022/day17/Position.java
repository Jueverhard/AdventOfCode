package calendar.year._2022.day17;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Position {
    private long x;
    private long y;

    public void move(Direction direction, Limits limits) throws RockCantMoveDownException {
        switch (direction) {
            case LEFT:
                if (x > limits.getLeft()) {
                    x--;
                }
                break;
            case RIGHT:
                if (x < limits.getRight()) {
                    x++;
                }
                break;
            case BOTTOM:
                if (y > limits.getBottom()) {
                    y--;
                } else {
                    throw new RockCantMoveDownException();
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + direction);
        }
    }

    public Position add(Position position) {
        return new Position(this.x + position.x, this.y + position.y);
    }

    public Position copyOf() {
        return new Position(this.x, this.y);
    }
}
