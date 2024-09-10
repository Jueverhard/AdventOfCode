package calendar.year._2016.day08;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Function;

@AllArgsConstructor
public enum Direction {
    COLUMN(Position::getX),
    ROW(Position::getY);

    @Getter
    private final Function<Position, Integer> positionGetter;
}
