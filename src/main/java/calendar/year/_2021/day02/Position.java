package calendar.year._2021.day02;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Position {

    private int x;

    private int depth;

    public void move(Direction direction, int units) {
        switch (direction) {
            case UP:
                depth -= units;
                break;
            case DOWN:
                depth += units;
                break;
            case FORWARD:
                x += units;
                break;
        }
    }
}
