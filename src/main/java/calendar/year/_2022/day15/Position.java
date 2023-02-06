package calendar.year._2022.day15;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Position {

    private long x;

    private long y;

    public long computeManhattanDistance(Position position) {
        return Math.abs(this.x - position.x) + Math.abs(this.y - position.y);
    }
}
