package calendar.year._2018.day10;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Light {

    @Getter
    private int xPos;

    @Getter
    private int yPos;

    private int xDelta;

    private int yDelta;

    public void move() {
        xPos += xDelta;
        yPos += yDelta;
    }

    public void moveBack() {
        xPos -= xDelta;
        yPos -= yDelta;
    }
}
