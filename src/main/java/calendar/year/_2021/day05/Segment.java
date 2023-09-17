package calendar.year._2021.day05;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Segment {

    private Position start;

    private Position end;

    public List<Position> getAllPositions() {
        return this.start.computeIntermediatePositions(this.end);
    }
}
