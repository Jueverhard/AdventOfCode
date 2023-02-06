package calendar.year._2022.day09;

import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class RopePart2 extends Rope {

    private List<Position> knots;

    public RopePart2() {
        knots = IntStream.range(0, 10)
                .mapToObj(elt -> new Position(0, 0))
                .collect(Collectors.toList());
        tailPositions = new HashSet<>();
    }

    @Override
    public void moveHead(Direction direction, int nbMoves) {
        while (nbMoves > 0) {
            moveHead(direction);
            for (int i = 1; i < knots.size(); i++) {
                Position relativeTail = knots.get(i);
                Position relativeHead = knots.get(i - 1);
                relativeTail.follow(relativeHead);
            }
            Position tail = knots.get(knots.size() - 1);
            tailPositions.add(tail.copy());
            nbMoves--;
        }
    }

    private void moveHead(Direction direction) {
        knots.get(0).move(direction);
    }
}
