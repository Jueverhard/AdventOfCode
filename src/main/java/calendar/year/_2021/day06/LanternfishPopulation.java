package calendar.year._2021.day06;

import org.apache.commons.lang3.ObjectUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LanternfishPopulation  {

    private static final int CYCLE_TIME = 6;
    private static final int TIME_TO_MATURITY = 2;

    private final LinkedList<Long> nbFishesAboutToCreate;

    public LanternfishPopulation(List<Integer> timers) {
        this.nbFishesAboutToCreate = new LinkedList<>();
        Map<Integer, Long> nbFishesPerTimer = timers.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for (int i = 0; i <= CYCLE_TIME + TIME_TO_MATURITY; i++) {
            long nbFishes = ObjectUtils.firstNonNull(nbFishesPerTimer.get(i), 0L);
            nbFishesAboutToCreate.add(nbFishes);
        }
    }

    public void passADay() {
        // Fetch the number of lantern-fishes that are about to give birth
        long nbFishesOfAge = nbFishesAboutToCreate.remove();

        // Add them to the end of the queue (cycle time + time to maturity)
        nbFishesAboutToCreate.add(nbFishesOfAge);

        // Add the parents to the right place of the queue (cycle time)
        long newNbFishesWithFullCycle = nbFishesAboutToCreate.get(CYCLE_TIME) + nbFishesOfAge;
        nbFishesAboutToCreate.set(CYCLE_TIME, newNbFishesWithFullCycle);
    }

    public long count() {
        return nbFishesAboutToCreate.stream()
                .reduce(0L, Long::sum);
    }
}
