package calendar.year._2023.day08;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HauntedWasteland extends Exercise {

    public HauntedWasteland(LocalDate date) {
        super(date);
    }

    @Override
    @SuppressWarnings("java:S2677") // Mute the warnings about unused method returns
    public String run(Part part, boolean testMode) throws IOException {
        List<Direction> directions;
        Set<ReachableNode> reachableNodes = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line = br.readLine();
            directions = Arrays.stream(line.split(""))
                    .map(Direction::valueOf)
                    .toList();

            // Skip a separating line
            br.readLine();

            Pattern pattern = Pattern.compile("\\w+");
            while (null != (line = br.readLine())) {
                List<String> nodeData = pattern.matcher(line).results()
                        .map(MatchResult::group)
                        .toList();
                reachableNodes.add(new ReachableNode(nodeData.get(0), nodeData.get(1), nodeData.get(2)));
            }
        }

        Map<String, ReachableNode> reachableNodesPerId = reachableNodes.stream()
                .collect(Collectors.toMap(ReachableNode::id, Function.identity()));

        long nbSteps = Part.PART_1 == part ?
                exercise1(reachableNodesPerId, directions) :
                exercise2(reachableNodesPerId, directions);


        return print(nbSteps);
    }

    private int exercise1(Map<String, ReachableNode> reachableNodesPerId, List<Direction> directions) {
        ReachableNode currentNode = reachableNodesPerId.get("AAA");
        Iterator<Direction> directionIterator = directions.iterator();

        int nbSteps = 0;
        while (!"ZZZ".equals(currentNode.id())) {
            if (!directionIterator.hasNext()) {
                directionIterator = directions.iterator();
            }
            currentNode = reachableNodesPerId.get(currentNode.getDestination(directionIterator.next()));
            nbSteps++;
        }
        return nbSteps;
    }

    private long exercise2(Map<String, ReachableNode> reachableNodesPerId, List<Direction> directions) {
        List<ReachableNode> initialNodes = reachableNodesPerId.values().stream()
                .filter(node -> node.id().endsWith("A"))
                .toList();
        Iterator<Direction> directionIterator = directions.iterator();

        Map<ReachableNode, Integer> nbSteps = initialNodes.stream()
                .collect(Collectors.toMap(Function.identity(), node -> 0));
        record CurrentNode(ReachableNode start, ReachableNode current) {}
        List<CurrentNode> currentNodes = initialNodes.stream()
                .map(node -> new CurrentNode(node, node))
                .toList();

        while (!currentNodes.isEmpty()) {
            if (!directionIterator.hasNext()) {
                directionIterator = directions.iterator();
            }
            Direction direction = directionIterator.next();

            // Compute the new current nodes
            currentNodes = currentNodes.parallelStream()
                    .map(currentNode -> new CurrentNode(
                            currentNode.start(),
                            reachableNodesPerId.get(currentNode.current().getDestination(direction))
                    ))
                    .collect(Collectors.toList());

            // Update the number of steps data
            currentNodes
                    .forEach(node -> nbSteps.merge(node.start(), 1, Integer::sum));

            List<CurrentNode> finishedNodes = currentNodes.stream()
                    .filter(node -> node.current().id().endsWith("Z"))
                    .toList();
            currentNodes.removeAll(finishedNodes);
        }

        return computeLeastCommonMultiple(nbSteps.values().stream().map(Long::valueOf).toList());
    }

    private long computeLeastCommonMultiple(List<Long> numbers) {
        return numbers.stream()
                .reduce(numbers.get(0), (a, b) -> a * b / findGCD(a, b));
    }

    private long findGCD(long a, long b) {
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }
}
