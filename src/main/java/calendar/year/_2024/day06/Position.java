package calendar.year._2024.day06;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class Position {

    private int x;
    private int y;

    public void move(Direction direction) {
        switch (direction) {
            case UP -> this.y--;
            case DOWN -> this.y++;
            case LEFT -> this.x--;
            case RIGHT -> this.x++;
        }
    }

    public boolean isOutOfBounds(int maxX, int maxY) {
        return this.x < 0 || this.x >= maxX || this.y < 0 || this.y >= maxY;
    }

    public Position copyOf() {
        return new Position(x, y);
    }
}
