package utils.pathfinding;

import lombok.Data;

import java.util.Map;
import java.util.Set;

@Data
public class Graph<T extends GraphNode> {

    private final Set<T> nodes;

    private final Map<T, Set<T>> connections;

    public Set<T> getConnections(T node) {
        return connections.get(node);
    }
}
