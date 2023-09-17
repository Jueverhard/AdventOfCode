package calendar.year._2020.day05;

import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class Dichotomy {

    private int min;

    private int max;

    public Optional<Integer> reduce(boolean isInGreatestHalf) {
        if (isInGreatestHalf) {
            this.min += Math.ceil((max - min) / 2.0);
        } else {
            this.max -= Math.ceil((max - min) / 2.0);
        }

        return min == max ? Optional.of(min) : Optional.empty();
    }
}
