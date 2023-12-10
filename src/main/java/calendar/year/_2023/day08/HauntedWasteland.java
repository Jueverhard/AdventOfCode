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

        return print(nbSteps);
    }
}
