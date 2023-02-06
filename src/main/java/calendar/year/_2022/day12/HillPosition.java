package calendar.year._2022.day12;

import utils.pathfinding.GraphNode;
import lombok.Data;

@Data
public class HillPosition implements GraphNode {

    private int x;

    private int y;

    private char altitude;

    public HillPosition(int x, int y, char altitude) {
        this.x = x;
        this.y = y;
        this.altitude = altitude;
    }

    public boolean isReachable(HillPosition position) {
        boolean isNearby = Math.abs(this.x - position.x) + Math.abs(this.y - position.y) == 1;
        boolean isTooElevated = position.altitude - this.altitude > 1;
        boolean isStart = (this.altitude == 'S' && position.altitude == 'a') ||
                (position.altitude == 'S' && this.altitude == 'a');
        boolean isEnd = (this.altitude == 'E' && position.altitude == 'z') ||
                (position.altitude == 'E' && this.altitude == 'z');

        return isNearby && (
                !isTooElevated || (
                        isStart || isEnd
                )
        );
    }
}
