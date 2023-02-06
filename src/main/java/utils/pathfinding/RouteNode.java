package utils.pathfinding;

import lombok.Data;

@Data
class RouteNode<T extends GraphNode> implements Comparable<RouteNode<T>> {

    private final T current;

    private T previous;

    private int routeScore;

    private int estimatedScore;

    RouteNode(T current) {
        this(current, null, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    RouteNode(T current, T previous, int routeScore, int estimatedScore) {
        this.current = current;
        this.previous = previous;
        this.routeScore = routeScore;
        this.estimatedScore = estimatedScore;
    }

    @Override
    public int compareTo(RouteNode other) {
        return Integer.compare(this.estimatedScore, other.estimatedScore);
    }
}
