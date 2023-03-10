package utils.pathfinding;

public interface Scorer<T extends GraphNode> {

    int computeCost(T from, T to);
}
