package calendar.year._2021.day06;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@AllArgsConstructor
public class LanternfishPopulation {

    private static final int CYCLE_TIME = 6;
    private static final int TIME_TO_MATURITY = 2;

    private List<Integer> timers;

    public void passADay() {
        // Count the number of lantern-fishes thar are going to be created
        long nbFishCreations = timers.stream()
                .filter(timer -> 0 == timer)
                .count();

        // Update every lantern-fishes timer
        timers = timers.stream()
                .map(this::computeNewTimerValue)
                .collect(Collectors.toList());

        // Creates the new lantern-fishes with extended initial cycle time
        timers.addAll(LongStream.range(0, nbFishCreations).mapToObj(_i -> CYCLE_TIME + TIME_TO_MATURITY).toList());
    }

    private int computeNewTimerValue(int value) {
        return 0 == value ? CYCLE_TIME : value - 1;
    }

    public int count() {
        return timers.size();
    }
}
