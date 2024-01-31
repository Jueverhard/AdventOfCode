package calendar.year._2023.day18;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Extremities {

    private long xMin;

    private long xMax;

    private long yMin;

    private long yMax;

    public void updateSelf(Position position) {
        if (position.x() < xMin) {
            this.xMin = position.x();
        } else if (position.x() > xMax) {
            this.xMax = position.x();
        }

        if (position.y() < yMin) {
            this.yMin = position.y();
        } else if (position.y() > yMax) {
            this.yMax = position.y();
        }
    }
}
