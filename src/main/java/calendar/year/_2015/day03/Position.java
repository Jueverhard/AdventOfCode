package calendar.year._2015.day03;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Position {

    private int x;

    private int y;

    public void move(Direction direction) {
        switch (direction) {
            case UP:
                y++;
                break;
            case DOWN:
                y--;
                break;
            case RIGHT:
                x++;
                break;
            case LEFT:
                x--;
                break;
        }
    }

    public Position copyOf() {
        return new Position(x, y);
    }
}
