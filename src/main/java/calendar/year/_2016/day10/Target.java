package calendar.year._2016.day10;

import java.util.Optional;

public interface Target {

    int getId();

    /**
     * @param value Value to add to the target.
     * @return The identifier of the looked for bot, if found.
     */
    Optional<Integer> addValue(int value);
}
