package utils.pathfinding;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

@Data
public class RouteFinder<T extends GraphNode> {

    private final Graph<T> graph;

    private final Scorer<T> nextNodeScorer;

    private final Scorer<T> targetScorer;

    public List<T> findRoute(T from, T to) {
        Map<T, RouteNode<T>> allNodes = new HashMap<>();
        Queue<RouteNode<T>> openSet = new PriorityQueue<>();

        RouteNode<T> start = new RouteNode<>(from, null, 0, targetScorer.computeCost(from, to));
        allNodes.put(from, start);
        openSet.add(start);

        while (!openSet.isEmpty()) {
//            System.out.println("Open Set contains: " + openSet.stream().map(RouteNode::getCurrent).collect(Collectors.toSet()));
            RouteNode<T> next = openSet.poll();
            Objects.requireNonNull(next);
//            System.out.println(("Looking at node: " + next));
            if (next.getCurrent().equals(to)) {
//                System.out.println("Found our destination!");

                List<T> route = new ArrayList<>();
                RouteNode<T> current = next;
                do {
                    route.add(0, current.getCurrent());
                    current = allNodes.get(current.getPrevious());
                } while (current != null);

//                System.out.println("Route: " + route);
                return route;
            }

            graph.getConnections(next.getCurrent()).forEach(connection -> {
                int newScore = next.getRouteScore() + nextNodeScorer.computeCost(next.getCurrent(), connection);
                RouteNode<T> nextNode = allNodes.getOrDefault(connection, new RouteNode<>(connection));
                allNodes.put(connection, nextNode);

                if (nextNode.getRouteScore() > newScore) {
                    nextNode.setPrevious(next.getCurrent());
                    nextNode.setRouteScore(newScore);
                    nextNode.setEstimatedScore(newScore + targetScorer.computeCost(connection, to));
                    openSet.add(nextNode);
//                    System.out.println("Found a better route to node: " + nextNode);
                }
            });
        }

        throw new IllegalStateException("No route found");
    }
}
