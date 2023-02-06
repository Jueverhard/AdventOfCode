package calendar.year._2022.day14;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Position {

    private int x;

    private int y;

    public Position(String fullPosition) {
        String[] positions = fullPosition.split(",");
        this.x = Integer.parseInt(positions[0]);
        this.y = Integer.parseInt(positions[1]);
    }

    public List<Position> generateSeparatingPositions(Position position) {
        if (this.x == position.x) {
            int start = Math.min(this.y, position.y);
            int end = Math.max(this.y, position.y);
            return IntStream.range(start + 1, end)
                    .mapToObj(yPos -> new Position(this.x, yPos))
                    .collect(Collectors.toList());
        } else if (this.y == position.y) {
            int start = Math.min(this.x, position.x);
            int end = Math.max(this.x, position.x);
            return IntStream.range(start + 1, end)
                    .mapToObj(xPos -> new Position(xPos, this.y))
                    .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("Positions must share one coordinate");
        }
    }

    public Position copy() {
        return new Position(this.x, this.y);
    }

    public Position getBottom() {
        return new Position(this.x, this.y + 1);
    }

    public Position getBottomLeft() {
        return new Position(this.x - 1, this.y + 1);
    }

    public Position getBottomRight() {
        return new Position(this.x + 1, this.y + 1);
    }
}
