package calendar.year._2016.day01;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@AllArgsConstructor
public class Position {

    private int x;

    private int y;

    private Direction direction;

    public void move(Instruction instruction) {
        direction = direction.turn(instruction.getTurn());
        switch (direction) {
            case N:
                y += instruction.getDistance();
                break;
            case E:
                x += instruction.getDistance();
                break;
            case S:
                y -= instruction.getDistance();
                break;
            case W:
                x -= instruction.getDistance();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public List<Position> moveAndReturnPositions(Instruction instruction) {
        Position oldPosition = this.copyOf();
        move(instruction);
        return generateSeparatingPositions(oldPosition);
    }

    public List<Position> generateSeparatingPositions(Position position) {
        List<Position> intermediatePositions = new ArrayList<>();
        if (this.x == position.x) {
            int start = Math.min(this.y, position.y) + 1;
            int end = Math.max(this.y, position.y);

            intermediatePositions.addAll(IntStream.range(start, end)
                    .mapToObj(yPos -> new Position(this.x, yPos, position.direction))
                    .collect(Collectors.toList()));
        } else if (this.y == position.y) {
            int start = Math.min(this.x, position.x) + 1;
            int end = Math.max(this.x, position.x);

            intermediatePositions.addAll(IntStream.range(start, end)
                    .mapToObj(xPos -> new Position(xPos, this.y, position.direction))
                    .collect(Collectors.toList()));
        } else {
            throw new IllegalArgumentException("Positions must share one coordinate");
        }

        intermediatePositions.add(this.copyOf());
        return intermediatePositions;
    }

    public int computeDistanceToOrigin() {
        return Math.abs(x) + Math.abs(y);
    }

    public Position copyOf() {
        return new Position(x, y, direction);
    }
}
