package calendar.year._2022.day08;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tree {

    private int positionX;

    private int positionY;

    private int size;

    public int computeDistance(Tree tree) {
        return Math.abs(this.getPositionX() - tree.getPositionX()) + Math.abs(this.getPositionY() - tree.getPositionY());
    }
}
