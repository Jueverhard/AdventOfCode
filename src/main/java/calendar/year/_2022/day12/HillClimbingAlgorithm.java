package calendar.year._2022.day12;

import utils.pathfinding.Graph;
import utils.pathfinding.RouteFinder;
import utils.pathfinding.Scorer;
import utils.Exercise;
import calendar.year.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HillClimbingAlgorithm extends Exercise {

    public HillClimbingAlgorithm(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            // Initialize the hill
            Hill hill = new Hill();
            String line;
            int y = 0;
            while ((line = br.readLine()) != null) {
                for (int x = 0; x < line.length(); x++) {
                    char elevation = line.charAt(x);
                    HillPosition newPos = new HillPosition(x, y, elevation);
                    hill.getPositions().add(newPos);
                    if ('E' == elevation) {
                        hill.setEnd(newPos);
                    } else if ('S' == elevation) {
                        hill.setStart(newPos);
                    }
                }
                y++;
            }
            hill.initializeConnections();

            // Setting up the graph for pathfinding
            Graph<HillPosition> graph = new Graph<>(hill.getPositions(), hill.getConnections());
            Scorer<HillPosition> scorer = new HillPositionScorer();
            RouteFinder<HillPosition> routeFinder = new RouteFinder<>(graph, scorer, scorer);

            // Searching for the best path
            int nbSteps;
            if (Part.PART_1.equals(part)) {
                List<HillPosition> path = routeFinder.findRoute(hill.getStart(), hill.getEnd());
                nbSteps = path.size() - 1;
            } else {
                List<List<HillPosition>> paths = hill.getPositions().stream()
                        .filter(position -> List.of('a', 'S').contains(position.getAltitude()))
                        .map(position -> {
                            try {
                                return routeFinder.findRoute(position, hill.getEnd());
                            } catch (IllegalStateException e) {
                                return null;
                            }
                        }).filter(Objects::nonNull)
                        .collect(Collectors.toList());
                nbSteps = paths.stream()
                        .mapToInt(List::size)
                        .min().orElseThrow() - 1;
            }

            return String.valueOf(nbSteps);
        }
    }
}
