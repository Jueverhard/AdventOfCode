package calendar.year._2018.day03;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Claim {

    private int id;

    private Position topLeftPosition;

    private int width;

    private int height;

    public List<Position> getAllClaimPositions() {
        return IntStream.range(topLeftPosition.getX(), topLeftPosition.getX() + width)
                .mapToObj(x -> IntStream.range(topLeftPosition.getY(), topLeftPosition.getY() + height)
                        .mapToObj(y -> new Position(x, y))
                        .collect(Collectors.toList())
                ).flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
