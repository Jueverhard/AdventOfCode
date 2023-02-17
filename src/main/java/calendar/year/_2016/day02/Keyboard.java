package calendar.year._2016.day02;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Keyboard {
    private List<Row> rows;

    private int xIndex;

    private int yIndex;

    /**
     * Updates the keyboard state following a given movement instruction
     *
     * @param direction The direction to follow
     */
    public void update(Direction direction) {
        switch (direction) {
            case R:
                // Index cannot be greater than the selected row number of keys
                if (xIndex == Math.min(xIndex + 1, getRowChars(yIndex).size() - 1) || ' ' == getRowChars(yIndex).get(xIndex + 1)) {
                    return;
                }

                xIndex++;
                break;
            case L:
                // Index cannot be negative
                if (xIndex == Math.max(xIndex - 1, 0) || ' ' == getRowChars(yIndex).get(xIndex - 1)) {
                    return;
                }

                xIndex--;
                break;
            case U:
            case D:
                if (isMoveLegit(direction)) {
                    yIndex += Direction.U.equals(direction) ? -1 : 1;
                }
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public char getKey() {
        return rows.get(yIndex).getKey(xIndex);
    }

    private List<Character> getRowChars(int index) {
        return rows.get(index).getChars();
    }

    /**
     * Checks whether the keyboard can switch its active row up or down
     *
     * @param direction The direction to follow
     * @return True if the up/down row can be the new active one
     */
    private boolean isMoveLegit(Direction direction) {
        switch (direction) {
            case U:
                try {
                    // A key must be defined for the move to be legit
                    return ' ' != getRowChars(yIndex - 1).get(xIndex);
                } catch (IndexOutOfBoundsException e) {
                    // Can't go up if already on top
                    return false;
                }
            case D:
                try {
                    // A key must be defined for the move to be legit
                    return ' ' != getRowChars(yIndex + 1).get(xIndex);
                } catch (IndexOutOfBoundsException e) {
                    // Can't go down if already on last row
                    return false;
                }
            default:
                throw new IllegalArgumentException();
        }
    }
}
