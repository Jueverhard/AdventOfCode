package calendar.year._2022.day16;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class ValveOpening {

    private Valve valve;

    private int releasedPressure;

    private List<ValveOpening> openableValves;

    public ValveOpening(Valve valve, int releasedPressure) {
        this.valve = valve;
        this.releasedPressure = releasedPressure;
        this.openableValves = new ArrayList<>();
    }

    public Set<Integer> computeReleasablePressures() {
        if (openableValves.isEmpty()) {
            return Set.of(releasedPressure);
        } else {
            return openableValves.stream()
                    .map(openableValve -> openableValve.computeReleasablePressures().stream()
                            .map(releasablePressure -> releasedPressure + releasablePressure)
                            .collect(Collectors.toSet())
                    )
                    .flatMap(Set::stream)
                    .collect(Collectors.toSet());
        }
    }
}
