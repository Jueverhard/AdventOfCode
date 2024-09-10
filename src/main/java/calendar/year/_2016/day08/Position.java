package calendar.year._2016.day08;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Position {

    private static int screenWidth;

    private static int screenHeight;

    @Getter
    private int x;

    @Getter
    private int y;

    public void rotate(Direction direction, int range) {
        if (Direction.ROW == direction) {
            rotateRow(range);
        } else {
            rotateColumn(range);
        }
    }

    private void rotateColumn(int range) {
        this.y = (this.y + range) % screenHeight;
    }

    private void rotateRow(int range) {
        this.x = (this.x + range) % screenWidth;
    }

    public static void setScreenLimits(int screenWidth, int screenHeight) {
        Position.screenWidth = screenWidth;
        Position.screenHeight = screenHeight;
    }
}
