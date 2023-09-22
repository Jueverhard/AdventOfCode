package calendar.year._2021.day05;

import lombok.AllArgsConstructor;
import utils.enums.Part;

import java.util.List;

@AllArgsConstructor
public class Segment {

    private Position start;

    private Position end;

    public List<Position> getAllPositions(Part part) {
        return this.start.computeIntermediatePositions(this.end, part);
    }
}
