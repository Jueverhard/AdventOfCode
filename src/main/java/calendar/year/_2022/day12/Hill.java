package calendar.year._2022.day12;

import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class Hill {

    private Set<HillPosition> positions = new HashSet<>();

    private HillPosition start;

    private HillPosition end;

    private Map<HillPosition, Set<HillPosition>> connections = new HashMap<>();

    public void initializeConnections() {
        for (HillPosition position : this.positions) {
            Set<HillPosition> reachablePositions = this.positions.stream()
                    .filter(position::isReachable)
                    .collect(Collectors.toSet());
            this.connections.put(position, reachablePositions);
        }
    }
}
