package calendar.year._2022.day09;

import lombok.Data;

import java.util.HashSet;

@Data
public class RopePart1 extends Rope {

    private Position head;

    private Position tail;

    public RopePart1() {
        head = new Position(0, 0);
        tail = new Position(0, 0);
        tailPositions = new HashSet<>();
    }

    @Override
    public void moveHead(Direction direction, int nbMoves) {
        while (nbMoves > 0) {
            moveHead(direction);
            tail.follow(head);
            tailPositions.add(tail.copy());
            nbMoves--;
        }
    }

    private void moveHead(Direction direction) {
        head.move(direction);
    }
}
