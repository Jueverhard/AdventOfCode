package calendar.year._2020.day05;

import java.util.Iterator;
import java.util.Optional;

public class Seat {

    private String specification;

    private Placement placement;

    public Seat(String specification) {
        this.specification = specification;
    }

    public Placement computePlacement() {
        Iterator<Character> charIterator = specification.chars()
                .mapToObj(charCode -> (char) charCode)
                .iterator();
        Dichotomy dichotomyRow = new Dichotomy(0, 127);
        Optional<Integer> optRow;
        do {
            optRow = dichotomyRow.reduce('B' == charIterator.next());
        } while (optRow.isEmpty());

        Dichotomy dichotomyColumn = new Dichotomy(0, 7);
        Optional<Integer> optColumn;
        do {
            optColumn = dichotomyColumn.reduce('R' == charIterator.next());
        } while (optColumn.isEmpty());

        return new Placement(optRow.get(), optColumn.get());
    }
}
