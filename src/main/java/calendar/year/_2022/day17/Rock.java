package calendar.year._2022.day17;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.Set;

@AllArgsConstructor
public class Rock {

    @Getter
    private final Set<Position> relativePositions;

    @Getter
    private final Limits limits;

    @Getter
    @Setter
    private Position bottomLeft;

    public Rock(Set<Position> relativePositions) {
        this.relativePositions = relativePositions;
        this.limits = new Limits(
                this.relativePositions.stream().max(Comparator.comparing(Position::getY)).orElseThrow().getY(),
                this.relativePositions.stream().max(Comparator.comparing(Position::getX)).orElseThrow().getX(),
                this.relativePositions.stream().min(Comparator.comparing(Position::getY)).orElseThrow().getY(),
                this.relativePositions.stream().min(Comparator.comparing(Position::getX)).orElseThrow().getX()
        );
        this.bottomLeft = new Position(0L, 0L);
    }

    public Rock copyOf() {
        return new Rock(this.relativePositions, this.limits, this.bottomLeft.copyOf());
    }
}
